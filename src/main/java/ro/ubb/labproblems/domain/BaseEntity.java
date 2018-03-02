package ro.ubb.labproblems.domain;

/**
 * Used as a base model for all the domain entities.
 * Implies that any entity has to be uniquely identifiable
 *
 * @param <ID> The type of the class unique identifier
 */
public interface BaseEntity<ID> {

    /**
     * @return a value that uniquely identifies this entity
     */
    ID getIdentifier();
}
