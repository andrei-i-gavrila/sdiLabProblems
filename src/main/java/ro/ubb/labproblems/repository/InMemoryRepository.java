package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

/**
 * Implements the general {@link Repository Repository} interface, stores entities in a map
 *
 * @param <ID> The identifier of the entity, will be used as the key
 * @param <T>  The type of the entity we create this repository for
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    /**
     * Storage for the elements
     */
    protected Map<ID, T> elements;
    /**
     * Validator of type T
     */
    private Validator<T> validator;

    /**
     * Constructor for the InMemoryRepository
     * @param validator Validator of type T
     */
    public InMemoryRepository(Validator<T> validator) {
        nullGuard(validator);
        this.validator = validator;
        elements = new HashMap<>();
    }

    /**
     * Checks if there is an entity registered with the given id
     * @param id must be not null.
     * @return The entity, if that exists
     */
    @Override
    public Optional<T> find(ID id) {
        nullGuard(id);
        return Optional.ofNullable(elements.get(id));
    }

    /**
     * All entities
     * @return An iterable version of all entities
     */
    @Override
    public Iterable<T> findAll() {
        return elements.values();
    }

    /**
     * Stores a new entity in the repository
     * @param entity the entity to be stored
     * @return
     * @throws ValidatorException Is thrown, if the input data of the entity was not valid
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        nullGuard(entity);

        validator.validate(entity);
        // TODO if
        if (elements.containsKey(entity.getIdentifier())) {
            return Optional.of(entity);
        }
        elements.put(entity.getIdentifier(), entity);

        return Optional.empty();

    }

    /**
     * Removes an entity from the repository, if that exists
     * @param id must not be null.
     * @return
     */
    @Override
    public Optional<T> delete(ID id) {
        nullGuard(id);

        return Optional.ofNullable(elements.remove(id));
    }

    /**
     * Updates an entity(it's identifier can not be updated, the rest only)
     * @param entity Entity to be updated
     * @return
     * @throws ValidatorException Is thrown, if the input data was invalid
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        nullGuard(entity);

        validator.validate(entity);


        if (elements.containsKey(entity.getIdentifier())) {
            elements.put(entity.getIdentifier(), entity);

            return Optional.empty();
        }

        return Optional.of(entity);
    }
}
