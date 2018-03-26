package ro.ubb.labproblems.domain.validators;

public interface Validator<T> {

    /**
     * Validates a given entity
     *
     * @param entity must not be null
     * @throws IllegalArgumentException if the entity is null
     * @throws ValidatorException       if the entity is null
     */
    void validate(T entity) throws ValidatorException;
}
