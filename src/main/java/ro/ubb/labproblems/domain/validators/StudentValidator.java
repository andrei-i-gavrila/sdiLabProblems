package ro.ubb.labproblems.domain.validators;

import ro.ubb.labproblems.domain.entities.Student;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

/**
 * Validator class for the student-type entities
 */
public class StudentValidator implements Validator<Student> {

    /**
     * Validates a given student entity
     *
     * @param entity The entity to be validated
     * @throws ValidatorException Is thrown, if the entity is invalid in some way
     */
    @Override
    public void validate(Student entity) throws ValidatorException {
        nullGuard(entity);

        if (entity.getGroupNumber() / 10 % 10 > 3) {
            throw new ValidatorException("Second digit of group can only be <= 3", entity);
        }

        if (entity.getRegistrationNumber().length() != 4) {
            throw new ValidatorException("Registration number has to be 4 digits", entity);
        }

        if (entity.getName().length() == 0) {
            throw new ValidatorException("Students must have a name", entity);
        }
    }

}
