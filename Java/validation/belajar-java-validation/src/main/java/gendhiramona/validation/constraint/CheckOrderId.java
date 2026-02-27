package gendhiramona.validation.constraint;

import gendhiramona.validation.enums.CaseMode;
import gendhiramona.validation.group.CreditCardPaymentGroup;
import gendhiramona.validation.group.VirtualAccountPaymentGroup;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@CheckCase(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
        mode = CaseMode.UPPER, message = "{payment.orderId.upper}")
@NotBlank(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
        message = "{payment.orderId.notblank}"
)
@Size(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
        min = 1, max = 10, message = "{payment.orderId.size}")
@Documented
@Constraint(validatedBy = {})
@Target({FIELD})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface CheckOrderId {

    String message() default "invalid order id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
