package gendhiramona.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ConstraintDescriptorTest extends AbstractValidatorTest{

    @Test
    void testConstraintDescriptor() {

        Person person = new Person();

        Set<ConstraintViolation<Person>> violations = validator.validate(person);

        for (ConstraintViolation<Person> violation : violations) {
            ConstraintDescriptor<?> descriptor = violation.getConstraintDescriptor();
            System.out.println("Descriptor Annotation: " + descriptor.getAnnotation());
            System.out.println("Descriptor Attributes: " + descriptor.getAttributes());
            System.out.println("Descriptor Groups: " + descriptor.getGroups());
            System.out.println("Descriptor Payload: " + descriptor.getPayload());
            System.out.println("=====================================");
        }
    }
}
