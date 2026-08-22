package gendhiramona.validation;

import gendhiramona.validation.group.CreditCardPaymentGroup;
import org.junit.jupiter.api.Test;

public class GroupConvertTest extends AbstractValidatorTest{

    @Test
    void testGroupConvert() {

        Payment payment = new Payment();
        payment.setOrderId("0001");
        payment.setAmount(10_000_000L);
        payment.setCreditCard("4111111111111111");
        payment.setCustomer(new Customer());

        validateWithGroups(payment, CreditCardPaymentGroup.class);
    }
}
