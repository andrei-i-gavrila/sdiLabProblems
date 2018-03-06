package ro.ubb.labproblems.domain.validators;

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Problem;

import static org.junit.Assert.*;

public class ProblemValidatorTest {

    private final ProblemValidator validator = new ProblemValidator();

    @Test
    public void validateCorrect() {
        try {
            validator.validate(new Problem("title", "description"));
            validator.validate(new Problem("t", "d"));
        } catch (ValidatorException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void validateTitleNotEmpty() {
        Problem invalidTitleProblem = new Problem("", "asd");
        try {
            validator.validate(invalidTitleProblem);
        } catch (ValidatorException e) {
            assertEquals("The title can't be empty", e.getMessage());
            assertEquals(invalidTitleProblem, e.getInvalidObject());
        }
    }

    @Test
    public void validateDescriptionNotEmpty() {
        Problem invalidDescriptionProblem = new Problem("asd", "");
        try {
            validator.validate(invalidDescriptionProblem);
        } catch (ValidatorException e) {
            assertEquals("The description can't be empty", e.getMessage());
            assertEquals(invalidDescriptionProblem, e.getInvalidObject());
        }
    }
}