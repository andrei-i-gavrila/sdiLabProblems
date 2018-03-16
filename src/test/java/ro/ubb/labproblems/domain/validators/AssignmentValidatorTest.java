package ro.ubb.labproblems.domain.validators;

import org.junit.Before;
import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AssignmentValidatorTest {

    private Repository<Integer, Student> studentRepository;
    private Repository<String, Problem> problemRepository;
    private AssignmentValidator assignmentValidator;

    @Before
    public void setUp() throws Exception {
        studentRepository = new InMemoryRepository<>(new StudentValidator());
        problemRepository = new InMemoryRepository<>(new ProblemValidator());

        assignmentValidator = new AssignmentValidator(studentRepository, problemRepository);
    }

    @Test
    public void validatesCorrectEntity() throws ValidatorException {
        studentRepository.save(new Student(1234, "Andrei", 926));
        problemRepository.save(new Problem("knapsack", "dp problem"));

        try {
            assignmentValidator.validate(new Assignment("knapsack", 1234));
        } catch (ValidatorException e) {
            fail("It should not fail");
        }
    }

    @Test
    public void failsNoStudent() throws ValidatorException {
        problemRepository.save(new Problem("asd", "asddd"));

        try {
            assignmentValidator.validate(new Assignment("asd", 1234));
            fail();
        } catch (ValidatorException e) {
            assertEquals("Registration number does not exist", e.getMessage());
        }
    }

    @Test
    public void failsNoProblem() throws ValidatorException {
        studentRepository.save(new Student(1234, "asd", 927));

        try {
            assignmentValidator.validate(new Assignment("ddsd", 1234));
            fail();
        } catch (ValidatorException e) {
            assertEquals("Problem title does not exist", e.getMessage());
        }
    }

    @Test
    public void failsNegativeGrade() throws ValidatorException {
        studentRepository.save(new Student(1234, "Andrei", 926));
        problemRepository.save(new Problem("knapsack", "dp problem"));

        try {
            Assignment assignment = new Assignment("knapsack", 1234);
            assignment.setGrade(-1.0);
            assignmentValidator.validate(assignment);
            fail();
        } catch (ValidatorException e) {
            assertEquals("Grade cannot be negative", e.getMessage());
        }
    }

}