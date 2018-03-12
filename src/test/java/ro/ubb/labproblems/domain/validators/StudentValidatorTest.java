package ro.ubb.labproblems.domain.validators;

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test every function of {@link StudentValidator StudentValidator}, supporting JUnit testing
 */
public class StudentValidatorTest {

    private StudentValidator validator = new StudentValidator();

    @Test
    public void validateCorrect() {
        try {
            validator.validate(new Student(1234, "andrei", 923));
            validator.validate(new Student(2020, "d", 933));
        } catch (ValidatorException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void validateRegistrationNumber() {
        Student invalidRegistrationNumberStudent = new Student(123, "asd", 123);
        try {
            validator.validate(invalidRegistrationNumberStudent);
            fail();
        } catch (ValidatorException e) {
            assertEquals("Registration number has to be 4 digits", e.getMessage());
            assertEquals(invalidRegistrationNumberStudent, e.getInvalidObject());
        }
    }

    @Test
    public void validateNameNotEmpty() {
        Student invalidNameStudent = new Student(1234, "", 123);
        try {
            validator.validate(invalidNameStudent);
            fail();
        } catch (ValidatorException e) {
            assertEquals("Students must have a name", e.getMessage());
            assertEquals(invalidNameStudent, e.getInvalidObject());
        }
    }

    @Test
    public void validateGroupNotCorrect() {
        Student invalidGroupStudent = new Student(1234, "asd", 950);
        try {
            validator.validate(invalidGroupStudent);
            fail();
        } catch (ValidatorException e) {
            assertEquals("Second digit of group can only be <= 3", e.getMessage());
            assertEquals(invalidGroupStudent, e.getInvalidObject());
        }
    }


}