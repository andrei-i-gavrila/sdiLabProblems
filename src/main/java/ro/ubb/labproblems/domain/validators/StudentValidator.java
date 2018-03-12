package ro.ubb.labproblems.domain.validators;

import ro.ubb.labproblems.domain.entities.Student;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

public class StudentValidator implements Validator<Student> {


    @Override
    public void validate(Student entity) throws ValidatorException {
        nullGuard(entity);

        if (entity.getGroupNumber() / 10 % 10 > 3) {
            throw new ValidatorException("Second digit of group can only be <= 3", entity);
        }

        if (entity.getRegistrationNumber().toString().length() != 4) {
            throw new ValidatorException("Registration number has to be 4 digits", entity);
        }

        if (entity.getName().length() == 0) {
            throw new ValidatorException("Students must have a name", entity);
        }
    }

}
