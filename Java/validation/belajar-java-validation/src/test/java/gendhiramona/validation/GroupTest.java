package gendhiramona.validation;

import gendhiramona.validation.group.CreditCardPaymentGroup;
import gendhiramona.validation.group.VirtualAccountPaymentGroup;
import org.junit.jupiter.api.Test;

public class GroupTest extends AbstractValidatorTest{

    @Test
    void testCreditCardGroup() {

        Payment payment = new Payment();
        payment.setOrderId("0001");
        payment.setAmount(10_000_000L);
        payment.setCreditCard("123");

        validateWithGroups(payment, CreditCardPaymentGroup.class);
    }

    @Test
    void testVirtualAccountGroup() {

        Payment payment = new Payment();
        payment.setOrderId("0001");
        payment.setAmount(10_000_000L);
        payment.setVirtualAccount("");

        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }
}
