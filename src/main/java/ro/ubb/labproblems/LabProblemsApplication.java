package ro.ubb.labproblems;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LabProblemsApplication {

    public static void main(String... args) throws IOException {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "asd");

        ObjectMapper om = new ObjectMapper();
        System.out.println(om.readValue(om.writeValueAsString(map), map.getClass()));
    }
}
