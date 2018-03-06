package ro.ubb.labproblems.domain.validators;

/**
 * Created by Sandy on 3/5/2018.
 */

import ro.ubb.labproblems.domain.entities.Problem;

public class ProblemValidator implements Validator<Problem> {

    @Override
    public void validate(Problem entity) throws ValidatorException {
        if (entity.getTitle().length() == 0) {
            throw new ValidatorException("The title can't be empty", entity);
        }

        if (entity.getDescription().length() == 0) {
            throw new ValidatorException("The description can't be empty", entity);
        }
    }

}
