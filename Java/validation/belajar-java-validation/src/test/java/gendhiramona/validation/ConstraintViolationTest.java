package gendhiramona.validation;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ConstraintViolationTest extends AbstractValidatorTest {

    @Test
    void testValidationFailed() {

        Person person = new Person();

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(2, violations.size());

        validate(person);
    }

    @Test
    void testValidationFailedSize() {

        Person person = new Person();
        person.setFirstName("asndkasjndkajsndkjansdkjandjaskjna");
        person.setLastName("asndkasjndkajsndkjansdkjandjaskjna");

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(2, violations.size());

        validate(person);
    }

    @Test
    void testValidationFailedMixed() {

        Person person = new Person();
        person.setFirstName("asndkasjndkajsndkjansdkjandjaskjna");
//        person.setLastName();

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(2, violations.size());

        validate(person);
    }

    @Test
    void testValidationSuccess() {

        Person person = new Person();
        person.setFirstName("Gendhi");
        person.setLastName("Ramona");

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(0, violations.size());

    }
}
