package gendhiramona.springvalidation;

import gendhiramona.springvalidation.data.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    void personNotValid() {
        Person person = new Person("", "");
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        Assertions.assertFalse(validate.isEmpty());
        Assertions.assertEquals(2, validate.size());
    }

    @Test
    void personValid() {
        Person person = new Person("1", "Gendhiramona");
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        Assertions.assertTrue(validate.isEmpty());
    }
}
