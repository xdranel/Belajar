package gendhiramona.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JavaBeanTest {

    @Test
    void createJsonFromObject() throws JsonProcessingException {
        Person person = new Person();
        person.setId("1");
        person.setName("John");
        person.setHobbies(List.of("music", "movies"));

        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("New York");
        address.setCountry("USA");
        person.setAddress(address);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void readObjectFromJson() throws JsonProcessingException {
        String json = """
                {"id":"1","name":"John","hobbies":["music","movies"],"address":{"street":"123 Main St","city":"New York","country":"USA"}}
                """;

        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);

        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("John", person.getName());
        Assertions.assertEquals(List.of("music", "movies"), person.getHobbies());
        Assertions.assertEquals("123 Main St", person.getAddress().getStreet());
        Assertions.assertEquals("New York", person.getAddress().getCity());
        Assertions.assertEquals("USA", person.getAddress().getCountry());
    }
}
