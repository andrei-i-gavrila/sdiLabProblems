package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.BaseEntity;
import ro.ubb.labproblems.domain.validators.ValidatorException;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 *
 * @author radu.
 */
public interface Repository<ID, T extends BaseEntity<ID>> {

    /**
     * Find the entity with the given {@code id}.
     *
     * @param id must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws NullPointerException if the given id is null.
     */
    Optional<T> find(ID id);

    /**
     * @return all entities.
     */
    Collection<T> all();

    /**
     * Saves the given entity.
     *
     * @param entity must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws NullPointerException if the given entity is null.
     */
    Optional<T> save(T entity) throws ValidatorException;

    /**
     * Removes the entity with the given id.
     *
     * @param id must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws NullPointerException if the given id is null.
     */
    Optional<T> delete(ID id);

    /**
     * Updates the given entity.
     *
     * @param entity must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     * entity.
     * @throws NullPointerException if the given entity is null.
     */
    Optional<T> update(T entity) throws ValidatorException;
}
