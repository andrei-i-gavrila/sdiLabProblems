package ro.ubb.labproblems.repository;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;

public class YamlRepository<ID, T extends BaseEntity<ID>> extends AbstractFileRepository<ID, T> {
    public YamlRepository(Validator<T> validator, String filename) {
        super(validator, filename, new YAMLMapper());
    }
}
