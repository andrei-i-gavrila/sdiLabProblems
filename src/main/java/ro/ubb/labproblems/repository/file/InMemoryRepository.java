package ro.ubb.labproblems.repository.file;

import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;

import java.util.Optional;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

public class InMemoryRepository<T extends BaseEntity<String>> implements Repository<String, T> {

    protected Class<T> type;
    protected StorageProvider storage;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator, StorageProvider storage, Class<T> type) {
        nullGuard(validator);
        this.validator = validator;
        this.type = type;
        this.storage = storage;
    }

    @Override
    public Optional<T> find(String id) {
        nullGuard(id);
        return storage.get(type).stream().filter(t -> t.getIdentifier().equals(id)).findFirst();
    }

    @Override
    public Iterable<T> findAll() {
        return storage.get(type);
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        nullGuard(entity);

        validator.validate(entity);

        if (find(entity.getIdentifier()).isPresent()) {
            return Optional.of(entity);
        }

        storage.get(type).add(entity);

        return Optional.empty();

    }

    @Override
    public Optional<T> delete(String id) {
        Optional<T> entity = find(id);

        if (entity.isPresent()) {
            storage.get(type).removeIf(t -> t.getIdentifier().equals(id));
        }

        return entity;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        validator.validate(entity);

        Optional<T> oldEntity = delete(entity.getIdentifier());

        if (oldEntity.isPresent()) {
            return save(entity);
        }

        return Optional.of(entity);
    }
}
