package ro.ubb.labproblems.repository.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class AbstractFileRepository<T extends BaseEntity<String>> extends InMemoryRepository<T> {


    private final String filename;
    private final ObjectMapper mapper;

    public AbstractFileRepository(Validator<T> validator, String filename, ObjectMapper mapper, StorageProvider storage, Class<T> type) {
        super(validator, storage, type);
        this.filename = filename;
        this.mapper = mapper;

        loadData(type);
    }

    private void storeData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), storage.getStorage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData(Class<T> type) {
        try {
            storage.setStorage(mapper.readValue(new File(filename), Storage.class));
        } catch (IOException ignored) {
            ignored.printStackTrace();
            System.out.println("No data to load for " + type.getSimpleName());
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
