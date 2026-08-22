package gendhiramona.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonArrayTest {

    @Test
    void createJsonArray() throws JsonProcessingException {
        List<String> hobbies = List.of("music", "movies");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(hobbies);

        System.out.println(json);
    }

    @Test
    void readJsonArray() throws JsonProcessingException {
        String json = "[\"music\",\"movies\"]";

        ObjectMapper mapper = new ObjectMapper();
        List<String> hobbies = mapper.readValue(json, new TypeReference<List<String>>() {});

        Assertions.assertEquals(List.of("music", "movies"), hobbies);
    }
}
