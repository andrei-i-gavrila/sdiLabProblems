package ro.ubb.labproblems.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class AbstractFileRepository<T extends BaseEntity<String>> extends InMemoryRepository<T> {


    private final String filename;
    private final ObjectMapper mapper;

    public AbstractFileRepository(Validator<T> validator, String filename, ObjectMapper mapper, Class<T> type) {
        super(validator);
        this.filename = filename;
        this.mapper = mapper;

        loadData(type);
    }

    private void storeData() {
        try {
            mapper.writeValue(new File(filename), elements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData(Class<T> type) {
        try {
            elements = mapper.readValue(new File(filename), TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, type));
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
    public Optional<T> delete(String id) {
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
