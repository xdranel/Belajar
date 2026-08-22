package gendhiramona.validation;

import gendhiramona.validation.group.CreditCardPaymentGroup;
import gendhiramona.validation.payload.EmailErrorPayload;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Payload;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class PayloadTest extends AbstractValidatorTest {

    @Test
    void testPayload() {

        Payment payment = new Payment();
        payment.setOrderId("0001");
        payment.setAmount(10_000_000L);
        payment.setCreditCard("411");

        Set<ConstraintViolation<Object>> violations = validator.validate(payment, CreditCardPaymentGroup.class);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Leaf Bean: " + violation.getLeafBean());
            System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value: " + violation.getInvalidValue());
            System.out.println("Property Path: " + violation.getPropertyPath());

            Set<Class<? extends Payload>> payload = violation.getConstraintDescriptor().getPayload();
            for (Class<? extends Payload> payloadClass : payload) {
                if (payloadClass == EmailErrorPayload.class) {
                    EmailErrorPayload emailErrorPayload = new EmailErrorPayload();
                    emailErrorPayload.sendEmail(violation);
                }
            }
            System.out.println("=====================================");
        }
    }
}
