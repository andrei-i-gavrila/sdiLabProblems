package ro.ubb.labproblems.repository.file;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;

public class YamlRepository<T extends BaseEntity<String>> extends AbstractFileRepository<T> {
    public YamlRepository(Validator<T> validator, StorageProvider storage, Class<T> type) {
        super(validator, "repository.yaml", new YAMLMapper(), storage, type);
    }
}
