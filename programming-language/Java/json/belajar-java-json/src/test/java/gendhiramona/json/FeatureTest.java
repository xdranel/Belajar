package gendhiramona.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeatureTest {

    @Test
    void mapperFeature() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
//        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        String json = """
                {"ID":"1","name":"John"}
                """;

        Person person = mapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("John", person.getName());
    }
}
