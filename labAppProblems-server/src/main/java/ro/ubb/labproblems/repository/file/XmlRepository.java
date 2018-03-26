package ro.ubb.labproblems.repository.file;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;

public class XmlRepository<T extends BaseEntity<String>> extends AbstractFileRepository<T> {

    public XmlRepository(Validator<T> validator, StorageProvider storage, Class<T> type) {
        super(validator, "repository.xml", new XmlMapper(), storage, type);
    }
}
