package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    protected final Map<ID, T> elements;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        requireNonNull(validator);
        this.validator = validator;
        elements = new HashMap<>();
    }


    @Override
    public Optional<T> find(ID id) {
        requireNonNull(id);
        return Optional.ofNullable(elements.get(id));
    }

    @Override
    public Collection<T> all() {
        return elements.values();
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        requireNonNull(entity);

        validator.validate(entity);
        // TODO if
        if (!elements.containsKey(entity.getIdentifier())) {
            elements.put(entity.getIdentifier(), entity);

            return Optional.empty();
        }

        return Optional.of(entity);
    }

    @Override
    public Optional<T> delete(ID id) {
        requireNonNull(id);

        return Optional.ofNullable(elements.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        requireNonNull(entity);

        validator.validate(entity);
        // TODO if
        if (elements.containsKey(entity.getIdentifier())) {
            elements.put(entity.getIdentifier(), entity);

            return Optional.empty();
        }

        return Optional.of(entity);
    }
}
