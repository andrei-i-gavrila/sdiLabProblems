package ro.ubb.labproblems.controller;

import org.junit.Before;
import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.AssignmentValidator;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

import static org.junit.Assert.assertEquals;

public class AssignmentControllerTest {

    public static final String REGISTRATION_NUMBER = "1234";
    public static final String PROBLEM_TITLE = "problem";

    private Repository<String, Student> studentRepository;
    private Repository<String, Problem> problemRepository;
    private Repository<String, Assignment> assignmentRepository;
    private AssignmentController assignmentController;

    @Before
    public void setUp() throws Exception {
        studentRepository = new InMemoryRepository<>(new StudentValidator());
        problemRepository = new InMemoryRepository<>(new ProblemValidator());
        assignmentRepository = new InMemoryRepository<>(new AssignmentValidator(studentRepository, problemRepository));
        assignmentController = new AssignmentController(assignmentRepository);

        studentRepository.save(new Student(REGISTRATION_NUMBER, "student", 123));
        problemRepository.save(new Problem(PROBLEM_TITLE, "description"));
    }

    @Test
    public void assign() {
        assertEquals("Assigned successfully", assignmentController.assign(PROBLEM_TITLE, REGISTRATION_NUMBER));
        assertEquals("Problem was already assigned to student", assignmentController.assign(PROBLEM_TITLE, REGISTRATION_NUMBER));
    }

    @Test
    public void grade() {
        assertEquals("Problem was not assigned to student", assignmentController.grade(PROBLEM_TITLE, REGISTRATION_NUMBER, 10.0));
        assignmentController.assign(PROBLEM_TITLE, REGISTRATION_NUMBER);
        assertEquals("Graded successfully", assignmentController.grade(PROBLEM_TITLE, REGISTRATION_NUMBER, 10.0));
    }

    @Test
    public void unassign() {
        assertEquals("No assignment was present", assignmentController.unassign(PROBLEM_TITLE, REGISTRATION_NUMBER));
        assignmentController.assign(PROBLEM_TITLE, REGISTRATION_NUMBER);
        assertEquals("Unassigned successfully", assignmentController.unassign(PROBLEM_TITLE, REGISTRATION_NUMBER));
    }

    @Test
    public void testMostAssignedProblem() {
        assignmentController.assign(PROBLEM_TITLE,REGISTRATION_NUMBER);
        assertEquals(assignmentController.mostAssignedProblem(),PROBLEM_TITLE);
        try {
            problemRepository.save(new Problem("title1", "description1"));
            studentRepository.save(new Student("2345","studentname",925));
        } catch (ValidatorException E) {}
        assignmentController.assign("title1","1234");
        assignmentController.assign("title1","2345");
        assertEquals(assignmentController.mostAssignedProblem(),"title1");
    }

    @Test
    public void testBestStudent() {
        assignmentController.assign(PROBLEM_TITLE,REGISTRATION_NUMBER);
        try {
            studentRepository.save(new Student("2345", "studentname", 925));
        } catch (ValidatorException E) {}

        assignmentController.assign(PROBLEM_TITLE,"2345");

        assignmentController.grade(PROBLEM_TITLE,REGISTRATION_NUMBER,5.2);
        assertEquals(assignmentController.bestStudent(),REGISTRATION_NUMBER);

        assignmentController.grade(PROBLEM_TITLE,"2345",8.8);
        assertEquals(assignmentController.bestStudent(),"2345");
    }
}