package ro.ubb.labproblems.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;

public class MapDeserializer<T> extends StdDeserializer<HashMap<String, T>> {

    public MapDeserializer(Class<?> vc) {
        super(vc);
    }

    public HashMap<String, T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (p.currentToken() == null) {
            return new HashMap<>();
        }
        HashMap<String, T> map = new HashMap<>();

        if (p.nextToken() != JsonToken.FIELD_NAME && !p.currentName().equals("entries")) {
            throw new RuntimeException("Expected entries field name");
        }
        if (p.nextToken() != JsonToken.START_OBJECT) {
            throw new RuntimeException("Expected start object");
        }
        while (true) {
            if (p.nextToken() != JsonToken.FIELD_NAME && !p.currentName().equals("entry")) {
                break;
            }
            if (p.nextToken() != JsonToken.START_OBJECT) {
                throw new RuntimeException("Expected start object");
            }
            if (p.nextToken() != JsonToken.FIELD_NAME && !p.currentName().equals("entry")) {
                throw new RuntimeException("Expected key field name");
            }
            String key = p.nextTextValue();

            if (p.nextToken() != JsonToken.FIELD_NAME && !p.currentName().equals("value")) {
                throw new RuntimeException("Expected value field name");
            }
            if (p.nextToken() != JsonToken.START_OBJECT) {
                throw new RuntimeException("Expected value field name");
            }

            @SuppressWarnings("unchecked") T val = (T) ctxt.readValue(p, _valueClass);

            if (p.nextToken() != JsonToken.END_OBJECT) {
                throw new RuntimeException("Expected end object");
            }

            map.put(key, val);

        }
        while (p.nextToken() != null) {
            if (p.currentToken() != JsonToken.END_OBJECT) {
                throw new RuntimeException("Expected end object");
            }
        }
        return map;
    }
}
