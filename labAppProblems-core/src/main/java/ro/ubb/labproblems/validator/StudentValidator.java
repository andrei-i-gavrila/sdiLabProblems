package ro.ubb.labproblems.validator;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.ubb.labproblems.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentValidator implements Validator<Student> {


    @Override
    public Optional<List<ValidationErrorDto>> validate(Student student) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        if (!StringUtils.hasText(student.getName())) {
            errors.add(new ValidationErrorDto("Students must have a name"));
        }

        if (student.getRegistrationNumber() == null) {
            errors.add(new ValidationErrorDto("Students must have a registration number"));
        }

        if (student.getRegistrationNumber().toString().length() != 4) {
            errors.add(new ValidationErrorDto("Student registration number must be exactly 4 digits long"));
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }
}
