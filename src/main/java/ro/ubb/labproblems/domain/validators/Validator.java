package ro.ubb.labproblems.domain.validators;

public interface Validator<T> {

    /**
     * Validates a given entity
     *
     * @param entity must not be null
     * @return true if the entity is valid, false otherwise
     * @throws IllegalArgumentException if the entity is null
     */
    boolean validate(T entity);
}
