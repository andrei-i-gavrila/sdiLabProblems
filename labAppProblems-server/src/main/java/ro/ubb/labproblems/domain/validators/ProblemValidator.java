package ro.ubb.labproblems.domain.validators;

import ro.ubb.labproblems.domain.entities.Problem;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

/**
 * Validator class for the problem-type entities
 */
public class ProblemValidator implements Validator<Problem> {

    /**
     * Validates a given problem entity
     *
     * @param entity The entity to be validated
     * @throws ValidatorException Is thrown, if the entity is invalid in some way
     */
    @Override
    public void validate(Problem entity) throws ValidatorException {
        nullGuard(entity);

        if (entity.getTitle().length() == 0) {
            throw new ValidatorException("The title can't be empty", entity);
        }

        if (entity.getDescription().length() == 0) {
            throw new ValidatorException("The description can't be empty", entity);
        }
    }

}
