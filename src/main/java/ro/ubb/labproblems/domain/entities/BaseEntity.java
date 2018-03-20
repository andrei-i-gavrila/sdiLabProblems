package ro.ubb.labproblems.domain.entities;

/**
 * Used as a base model for all the domain entities.
 * Implies that any entity has to be uniquely identifiable
 *
 * @param <ID> The type of the class unique identifier
 */
public abstract class BaseEntity<ID> {

    public BaseEntity() {

    }

    /**
     * @return a value that uniquely identifies this entity
     */
    public abstract ID getIdentifier();
}
