package gendhiramona.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class JsonObjectTest {

    @Test
    void createJson() throws JsonProcessingException {

        Map<String, Object> person = Map.of(
                "firstName", "John",
                "lastName", "Doe",
                "age", 30,
                "married", false,
                "address", Map.of(
                        "street", "123 Main St",
                        "city", "New York",
                        "state", "NY",
                        "country", "USA"
                ),
                "hobbies", new String[]{
                        "music", "movies"
                }
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void readJson() throws JsonProcessingException {
        String json = """
                {"lastName":"Doe","age":30,"firstName":"John","address":{"country":"USA","city":"New York","street":"123 Main St","state":"NY"},"married":false,"hobbies":["music","movies"]}
                """;

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> person = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        Assertions.assertEquals("John", person.get("firstName"));
        Assertions.assertEquals("Doe", person.get("lastName"));
        Assertions.assertEquals(30, person.get("age"));
        Assertions.assertEquals(false, person.get("married"));
    }
}
