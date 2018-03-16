package ro.ubb.labproblems.controller;

import org.junit.Before;
import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.AssignmentValidator;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

import static org.junit.Assert.assertEquals;

public class AssignmentControllerTest {

    public static final int REGISTRATION_NUMBER = 1234;
    public static final String PROBLEM_TITLE = "problem";

    private Repository<Integer, Student> studentRepository;
    private Repository<String, Problem> problemRepository;
    private Repository<Integer, Assignment> assignmentRepository;
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
    public void unnassign() {
        assertEquals("No assignment was present", assignmentController.unnassign(PROBLEM_TITLE, REGISTRATION_NUMBER));
        assignmentController.assign(PROBLEM_TITLE, REGISTRATION_NUMBER);
        assertEquals("Unassigned successfully", assignmentController.unnassign(PROBLEM_TITLE, REGISTRATION_NUMBER));
    }
}