package gendhiramona.validation;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;

public class ContainerDataTest extends AbstractValidatorTest {

    @Test
    void testContainerData() {

        Person person = new Person();
        person.setFirstName("Gendhi");
        person.setLastName("Ramona");
        person.setAddress(new Address());
        person.getAddress().setStreet("Jl. Ahmad Yani");
        person.getAddress().setCity("Jakarta");
        person.getAddress().setCountry("Indonesia");

        person.setHobbies(new ArrayList<>());
        person.getHobbies().add("");
        person.getHobbies().add("   ");
        person.getHobbies().add("Sport");

        validate(person);
    }
}
