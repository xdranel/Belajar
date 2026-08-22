package gendhiramona.validation;

import gendhiramona.validation.group.CreditCardPaymentGroup;
import org.junit.jupiter.api.Test;

public class ConstraintCompositionTest extends AbstractValidatorTest{

    @Test
    void testComposition() {

        Payment payment = new Payment();
        payment.setOrderId("128381273819237asdsada");

        validateWithGroups(payment, CreditCardPaymentGroup.class);
    }
}
