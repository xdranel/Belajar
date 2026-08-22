package gendhiramona.validation;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Set;

public class MethodValidationTest extends AbstractValidatorTest{

    @Test
    void testSayHello() throws NoSuchMethodException {

        Person person = new Person();
        String name = "";

        Method method = Person.class.getMethod("sayHello", String.class);

        Set<ConstraintViolation<Person>> violations = executableValidator
                .validateParameters(person, method, new Object[]{name});
        for (ConstraintViolation<Person> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Leaf Bean: " + violation.getLeafBean());
            System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value: " + violation.getInvalidValue());
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("=====================================");
        }
    }

    @Test
    void testFullName() throws NoSuchMethodException {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("");

        String returnValue = person.fullName();

        Method method = Person.class.getMethod("fullName");

        Set<ConstraintViolation<Person>> violations = executableValidator
                .validateReturnValue(person, method, returnValue);
        for (ConstraintViolation<Person> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Leaf Bean: " + violation.getLeafBean());
            System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value: " + violation.getInvalidValue());
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("=====================================");
        }
    }
}
