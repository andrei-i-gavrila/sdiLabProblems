package ro.ubb.labproblems.repository;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;

public class XmlRepository<ID, T extends BaseEntity<ID>> extends AbstractFileRepository<ID, T> {

    public XmlRepository(Validator<T> validator, String filename) {
        super(validator, filename, new XmlMapper().configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true));
    }
}
