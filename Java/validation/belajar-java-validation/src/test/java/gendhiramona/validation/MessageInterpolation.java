package gendhiramona.validation;

import gendhiramona.validation.group.VirtualAccountPaymentGroup;
import org.junit.jupiter.api.Test;

public class MessageInterpolation extends AbstractValidatorTest {

    @Test
    void testMessage() {

        Payment payment = new Payment();
        payment.setOrderId("0001813129321");
        payment.setAmount(10L);
        payment.setVirtualAccount("3847918");

        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }
}
