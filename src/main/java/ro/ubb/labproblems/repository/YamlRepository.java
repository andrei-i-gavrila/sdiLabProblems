package ro.ubb.labproblems.repository;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ro.ubb.labproblems.LabProblemsApplication;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;

public class YamlRepository<T extends BaseEntity<String>> extends AbstractFileRepository<T> {
    public YamlRepository(Validator<T> validator, Class<T> type) {
        super(validator, LabProblemsApplication.class.getResource("/" + type.getSimpleName() + "Repository.yaml").getPath(), new YAMLMapper(), type);
    }
}
