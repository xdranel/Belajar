package gendhiramona.validation;

import gendhiramona.validation.group.CreditCardPaymentGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.MessageInterpolator;
import org.hibernate.validator.internal.engine.MessageInterpolatorContext;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;

public class MessageInternationalizationTest extends AbstractValidatorTest {

    @Test
    void testI18N() {

        Locale.setDefault(new Locale("id", "ID"));

        Payment payment = new Payment();
        payment.setOrderId("1283981273918791391832");
        payment.setAmount(10L);

        validateWithGroups(payment, CreditCardPaymentGroup.class);
    }

    @Test
    void testMessageInterpolator() {

        Payment payment = new Payment();
        payment.setOrderId("1283981273918791391832");
        payment.setAmount(10L);

        Locale locale = new Locale("id", "ID");

        Set<ConstraintViolation<Object>> violations = validator.validate(payment, CreditCardPaymentGroup.class);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("Message Template: " + violation.getMessageTemplate());

            MessageInterpolator.Context context = new MessageInterpolatorContext(
                    violation.getConstraintDescriptor(), violation.getInvalidValue(), violation.getRootBeanClass(),
                    violation.getPropertyPath(), violation.getConstraintDescriptor().getAttributes(),
                    violation.getConstraintDescriptor().getAttributes(),
                    ExpressionLanguageFeatureLevel.VARIABLES, true
            );

            String message = messageInterpolator.interpolate(violation.getMessageTemplate(), context, locale);
            System.out.println("Message: " + message);

            System.out.println("=====================================");
        }
    }
}
