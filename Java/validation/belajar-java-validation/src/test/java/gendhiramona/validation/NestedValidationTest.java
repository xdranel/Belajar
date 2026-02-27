package gendhiramona.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class NestedValidationTest extends AbstractValidatorTest{

    @Test
    void testNested() {
        Person person = new Person();
        person.setFirstName("Gendhi");
        person.setLastName("Ramona");

        Address address = new Address();
        person.setAddress(address);

        validate(person);
    }
}
