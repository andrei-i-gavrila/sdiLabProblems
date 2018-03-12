package ro.ubb.labproblems.controller;

/**
 * Created by Sandy on 3/6/2018.
 */

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

import java.util.Optional;

import static org.junit.Assert.*;

public class StudentControllerTest {

    private static final String NAME = "student1";
    private static final int REG_NUMBER = 1234;
    private static final int FAKE_REG_NUMBER = 9494;
    private static final int GROUP = 925;

    private Student student = new Student(REG_NUMBER, NAME, GROUP);
    private Repository<Integer, Student> studentRepository = new InMemoryRepository<>(new StudentValidator());
    private StudentController studentController = new StudentController(studentRepository);

    @Test
    public void testAdd() {
        assertEquals("Student added successfully", studentController.add(NAME, REG_NUMBER, GROUP));
        assertTrue(studentRepository.find(REG_NUMBER).isPresent());
    }

    @Test
    public void testRemove() {
        studentController.add(NAME, REG_NUMBER, GROUP);
        assertEquals("No student with such registration ID was found", studentController.remove(FAKE_REG_NUMBER));
        assertEquals("Student removed successfully", studentController.remove(REG_NUMBER));
        assertFalse(studentRepository.find(REG_NUMBER).isPresent());
    }

    @Test
    public void testUpdate() {
        studentController.add(NAME, REG_NUMBER, GROUP);
        assertEquals("No student was found with the given registration number", studentController.update(NAME, FAKE_REG_NUMBER, GROUP));
        assertEquals("Student updated successfully", studentController.update(NAME + NAME, REG_NUMBER, GROUP));

        Optional<Student> studentFromRepository = studentRepository.find(REG_NUMBER);
        assertTrue(studentFromRepository.isPresent());
        assertNotEquals(studentFromRepository.get(), student);
        assertEquals(studentFromRepository.get().getName(), NAME + NAME);
    }

    @Test
    public void testPrintAll() {
        studentController.add(NAME, REG_NUMBER, GROUP);
        assertEquals(student.toString(), studentController.showAll());

        Student student2 = new Student(FAKE_REG_NUMBER, NAME, GROUP);
        studentController.add(NAME, FAKE_REG_NUMBER, GROUP);
        assertEquals(student + "\n" + student2, studentController.showAll());
    }

    @Test
    public void testValidationHappens() {
        assertEquals("Students must have a name", studentController.add("", REG_NUMBER, GROUP));
        assertEquals("Students must have a name", studentController.update("", REG_NUMBER, GROUP));
    }
}
