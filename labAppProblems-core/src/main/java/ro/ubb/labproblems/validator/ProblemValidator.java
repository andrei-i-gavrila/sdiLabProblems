package ro.ubb.labproblems.validator;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.ubb.labproblems.model.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProblemValidator implements Validator<Problem> {


    @Override
    public Optional<List<ValidationErrorDto>> validate(Problem problem) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        if (!StringUtils.hasText(problem.getTitle())) {
            errors.add(new ValidationErrorDto("Problems must have a title"));
        }

        if (!StringUtils.hasText(problem.getDescription())) {
            errors.add(new ValidationErrorDto("Problems must have a description"));
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }
}
