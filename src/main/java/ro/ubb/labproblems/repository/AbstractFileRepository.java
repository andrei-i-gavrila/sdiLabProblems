package ro.ubb.labproblems.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class AbstractFileRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {


    private final String filename;
    private final ObjectMapper mapper;

    public AbstractFileRepository(Validator<T> validator, String filename, ObjectMapper mapper) {
        super(validator);
        this.filename = filename;
        this.mapper = mapper;

        loadData();
    }

    private void storeData() {
        try {
            mapper.writeValue(new File(filename), elements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            elements = mapper.readValue(new File(filename), new TypeReference<Map<ID, T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> saveResult = super.save(entity);
        storeData();
        return saveResult;
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<T> result = super.delete(id);
        storeData();
        return result;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> updateResult = super.update(entity);
        storeData();
        return updateResult;
    }
}
