package ro.ubb.labproblems.service;

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.AssignmentValidator;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.file.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.repository.file.StorageProvider;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Class to test every function of {@link StudentServiceServer StudentService}, supporting JUnit testing
 */
public class StudentServiceTest {

    private static final String NAME = "student1";
    private static final String REG_NUMBER = "1234";
    private static final String FAKE_REG_NUMBER = "9494";
    private static final int GROUP = 925;

    private Student student = new Student(REG_NUMBER, NAME, GROUP);
    private StorageProvider storageProvider = new StorageProvider();
    private Repository<String, Student> studentRepository = new InMemoryRepository<>(new StudentValidator(), storageProvider, Student.class);
    private Repository<String, Problem> problemRepository = new InMemoryRepository<>(new ProblemValidator(), storageProvider, Problem.class);

    private Repository<String,Assignment> assignmentRepository = new InMemoryRepository<>(new AssignmentValidator(studentRepository, problemRepository), storageProvider, Assignment.class);
    private StudentService studentService = new StudentServiceServer(studentRepository, assignmentRepository);

    @Test
    public void testAdd() {
        assertEquals("Student added successfully", studentService.add(NAME, REG_NUMBER, GROUP));
        assertTrue(studentRepository.find(REG_NUMBER).isPresent());
    }

    @Test
    public void testRemove() {
        studentService.add(NAME, REG_NUMBER, GROUP);
        assertEquals("No student with such registration ID was found", studentService.remove(FAKE_REG_NUMBER));
        assertEquals("Student removed successfully", studentService.remove(REG_NUMBER));
        assertFalse(studentRepository.find(REG_NUMBER).isPresent());
    }

    @Test
    public void testUpdate() {
        studentService.add(NAME, REG_NUMBER, GROUP);
        assertEquals("No student was found with the given registration number", studentService.update(NAME, FAKE_REG_NUMBER, GROUP));
        assertEquals("Student updated successfully", studentService.update(NAME + NAME, REG_NUMBER, GROUP));

        Optional<Student> studentFromRepository = studentRepository.find(REG_NUMBER);
        assertTrue(studentFromRepository.isPresent());
        assertNotEquals(studentFromRepository.get(), student);
        assertEquals(studentFromRepository.get().getName(), NAME + NAME);
    }

    @Test
    public void testPrintAll() {
        studentService.add(NAME, REG_NUMBER, GROUP);
        assertEquals(student.toString(), studentService.showAll());

        Student student2 = new Student(FAKE_REG_NUMBER, NAME, GROUP);
        studentService.add(NAME, FAKE_REG_NUMBER, GROUP);
        assertEquals(student + "\n" + student2, studentService.showAll());
    }

    @Test
    public void testValidationHappens() {
        assertEquals("Students must have a name", studentService.add("", REG_NUMBER, GROUP));
        assertEquals("Students must have a name", studentService.update("", REG_NUMBER, GROUP));
    }

    @Test
    public void testFilterByGroup() {
        studentService.add(NAME, REG_NUMBER, GROUP);
        assertEquals("student1 1234 925", studentService.filterByGroup(GROUP));
        studentService.add("student2", "2345", 925);
        assertEquals("student1 1234 925, student2 2345 925", studentService.filterByGroup(925));
    }
}
