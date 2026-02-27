package gendhiramona.lombok;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PersonTest {

    @Test
    void testCreateViaConstructor() {

        var hobbies = new ArrayList<String>();
        hobbies.add("Read");
        hobbies.add("Write");

        var person = new Person("id", "Gendhi", 20, hobbies);
    }

    @Test
    void testCreateViaSetter() {

        var hobbies = new ArrayList<String>();
        hobbies.add("Read");
        hobbies.add("Write");

        var person = new Person();
        person.setId("id");
        person.setName("Gendhi");
        person.setAge(20);
        person.setHobbies(hobbies);
    }


    @Test
    void testCreateViaBuilder() {

        var person = Person.builder()
                .id("id")
                .name("Gendhi")
                .age(20)
                .hobby("Game")
                .hobby("Sport")
                .build();

        System.out.println(person);
    }
}
