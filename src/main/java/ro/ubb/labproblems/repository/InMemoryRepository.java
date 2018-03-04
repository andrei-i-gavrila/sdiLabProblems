package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    protected final Map<ID, T> elements;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        nullGuard(validator);
        this.validator = validator;
        elements = new HashMap<>();
    }


    @Override
    public Optional<T> find(ID id) {
        nullGuard(id);
        return Optional.ofNullable(elements.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        return elements.values();
    }

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

    @Override
    public Optional<T> delete(ID id) {
        nullGuard(id);

        return Optional.ofNullable(elements.remove(id));
    }

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
