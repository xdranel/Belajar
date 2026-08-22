package gendhiramona.reflection;

import gendhiramona.reflection.data.Person;
import gendhiramona.reflection.validation.Validator;
import org.junit.jupiter.api.Test;

public class AnnotationTest {

    @Test
    void testValidateUsingAnnotation() throws IllegalAccessException {

        Person person = new Person("John", "");
        Validator.validateNotBlank(person);
    }
}
