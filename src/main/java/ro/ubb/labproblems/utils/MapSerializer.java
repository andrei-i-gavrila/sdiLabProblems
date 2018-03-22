package ro.ubb.labproblems.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

public class MapSerializer extends StdSerializer<Map> {

    public MapSerializer() {
        this(null);
    }

    public MapSerializer(Class<Map> t) {
        super(t);
    }

    @Override
    public void serialize(Map value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectFieldStart("entries");
        value.forEach((o, o2) -> {
                    try {
                        gen.writeStartObject();
                        gen.writeObjectFieldStart("entry");
                        gen.writeStartObject();
                        gen.writeObjectFieldStart("key");
                        gen.writeObject(o);
                        gen.writeObjectFieldStart("value");
                        gen.writeObject(o2);
                        gen.writeEndObject();
                        gen.writeEndObject();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        gen.writeEndObject();
    }
}
