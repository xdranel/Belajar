package gendhiramona.validation;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Set;

public class ConstructorValidationTest extends AbstractValidatorTest{

    @Test
    void testValidateConstructorParameter() throws NoSuchMethodException {

        String firstName = "";
        String lastName = "";
        Address address = new Address();

        Constructor<?> constructor = Person.class.getConstructor(String.class, String.class, Address.class);

        Set<ConstraintViolation<Object>> violations = executableValidator
                .validateConstructorParameters(constructor, new Object[]{firstName, lastName, address});
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Leaf Bean: " + violation.getLeafBean());
            System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value: " + violation.getInvalidValue());
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("=====================================");
        }
    }

    @Test
    void testValidateConstructorReturnValue() throws NoSuchMethodException {

        String firstName = "";
        String lastName = "";
        Address address = new Address();

        Person person = new Person(firstName, lastName, address);

        Constructor<?> constructor = Person.class.getConstructor(String.class, String.class, Address.class);

        Set<ConstraintViolation<Object>> violations = executableValidator
                .validateConstructorReturnValue(constructor, person);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Leaf Bean: " + violation.getLeafBean());
            System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value: " + violation.getInvalidValue());
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("=====================================");
        }
    }
}
