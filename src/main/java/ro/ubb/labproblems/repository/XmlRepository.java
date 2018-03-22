package ro.ubb.labproblems.repository;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ro.ubb.labproblems.LabProblemsApplication;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.utils.MapDeserializer;
import ro.ubb.labproblems.utils.MapSerializer;

import java.util.HashMap;
import java.util.Map;

public class XmlRepository<T extends BaseEntity<String>> extends AbstractFileRepository<T> {

    public XmlRepository(Validator<T> validator, Class<T> type) {
        super(
                validator,
                LabProblemsApplication.class.getResource("/" + type.getSimpleName() + "Repository.xml").getPath(),
                new XmlMapper()
                        .registerModule(new SimpleModule()
                                .addSerializer(Map.class, new MapSerializer()))
                        .registerModule(new SimpleModule()
                                .addDeserializer(HashMap.class, new MapDeserializer<T>(type))),
                type);
    }
}
