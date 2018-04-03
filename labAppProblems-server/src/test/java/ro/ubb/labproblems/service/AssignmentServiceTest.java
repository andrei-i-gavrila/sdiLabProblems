package ro.ubb.labproblems.service;

import org.junit.Before;
import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.AssignmentValidator;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.repository.file.InMemoryRepository;
import ro.ubb.labproblems.repository.file.StorageProvider;

import static org.junit.Assert.assertEquals;

public class AssignmentServiceTest {

    public static final String REGISTRATION_NUMBER = "1234";
    public static final String PROBLEM_TITLE = "problem";

    private Repository<String, Student> studentRepository;
    private Repository<String, Problem> problemRepository;
    private Repository<String, Assignment> assignmentRepository;
    private AssignmentService assignmentService;
    private ProblemService problemService;
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        StorageProvider storageProvider = new StorageProvider();
        studentRepository = new InMemoryRepository<>(new StudentValidator(), storageProvider, Student.class);
        problemRepository = new InMemoryRepository<>(new ProblemValidator(), storageProvider, Problem.class);
        assignmentRepository = new InMemoryRepository<>(new AssignmentValidator(studentRepository, problemRepository), storageProvider, Assignment.class);
        assignmentService = new AssignmentServiceServer(assignmentRepository, studentRepository);
        problemService = new ProblemServiceServer(problemRepository, assignmentRepository);
        studentService = new StudentServiceServer(studentRepository, assignmentRepository);
        studentRepository.save(new Student(REGISTRATION_NUMBER, "student", 123));
        problemRepository.save(new Problem(PROBLEM_TITLE, "description"));
    }

    @Test
    public void assign() {
        assertEquals("Assigned successfully", assignmentService.assign(PROBLEM_TITLE, REGISTRATION_NUMBER));
        assertEquals("Problem was already assigned to student", assignmentService.assign(PROBLEM_TITLE, REGISTRATION_NUMBER));
    }

    @Test
    public void grade() {
        assertEquals("Problem was not assigned to student", assignmentService.grade(PROBLEM_TITLE, REGISTRATION_NUMBER, 10.0));
        assignmentService.assign(PROBLEM_TITLE, REGISTRATION_NUMBER);
        assertEquals("Graded successfully", assignmentService.grade(PROBLEM_TITLE, REGISTRATION_NUMBER, 10.0));
    }

    @Test
    public void unassign() {
        assertEquals("No assignment was present", assignmentService.unassign(PROBLEM_TITLE, REGISTRATION_NUMBER));
        assignmentService.assign(PROBLEM_TITLE, REGISTRATION_NUMBER);
        assertEquals("Unassigned successfully", assignmentService.unassign(PROBLEM_TITLE, REGISTRATION_NUMBER));
    }

    @Test
    public void testMostAssignedProblem() {
        assignmentService.assign(PROBLEM_TITLE, REGISTRATION_NUMBER);
        assertEquals(problemService.mostAssignedProblem(), PROBLEM_TITLE+"=1");
        try {
            problemRepository.save(new Problem("title1", "description1"));
            studentRepository.save(new Student("2345", "studentname", 925));
        } catch (ValidatorException E) {
        }
        assignmentService.assign("title1", "1234");
        assignmentService.assign("title1", "2345");
        assertEquals(problemService.mostAssignedProblem(), "title1=2");
    }

    @Test
    public void testBestStudent() {
        assignmentService.assign(PROBLEM_TITLE, REGISTRATION_NUMBER);
        try {
            studentRepository.save(new Student("2345", "studentname", 925));
        } catch (ValidatorException ignored) {
        }

        assignmentService.assign(PROBLEM_TITLE, "2345");

        assignmentService.grade(PROBLEM_TITLE, REGISTRATION_NUMBER, 5.2);
        assertEquals(studentService.bestStudent(), new Student(REGISTRATION_NUMBER, "student", 123).toString());

        assignmentService.grade(PROBLEM_TITLE, "2345", 8.8);
        assertEquals(studentService.bestStudent(),new Student("2345", "studentname", 925).toString());
    }
}