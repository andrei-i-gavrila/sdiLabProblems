package ro.ubb.labproblems.validator;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.ubb.labproblems.model.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentValidator implements Validator<Assignment> {


    @Override
    public Optional<List<ValidationErrorDto>> validate(Assignment assignment) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }
}
