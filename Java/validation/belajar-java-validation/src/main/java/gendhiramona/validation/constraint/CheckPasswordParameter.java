package gendhiramona.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CheckPasswordParameterValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, TYPE})
@Retention(RUNTIME)
public @interface CheckPasswordParameter {

    int passwordParam();

    int confirmPasswordParam();

    String message() default "password and confirm password must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
