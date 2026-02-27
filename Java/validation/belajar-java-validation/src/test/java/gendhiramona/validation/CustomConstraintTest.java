package gendhiramona.validation;

import gendhiramona.validation.group.CreditCardPaymentGroup;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class CustomConstraintTest extends AbstractValidatorTest{

    @Test
    void testCustomConstraint() {

        Locale.setDefault(new Locale("id", "ID"));

        Payment payment = new Payment();
        payment.setOrderId("abcd");

        validateWithGroups(payment, CreditCardPaymentGroup.class);
    }
}
