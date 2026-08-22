package gendhiramona.validation.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class CheckPasswordParameterValidator implements ConstraintValidator<CheckPasswordParameter, Object[]> {

    private int passwordParam;

    private int confirmPasswordParam;

    @Override
    public void initialize(CheckPasswordParameter constraintAnnotation) {
        passwordParam = constraintAnnotation.passwordParam();
        confirmPasswordParam = constraintAnnotation.confirmPasswordParam();
    }

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        String password = (String) value[passwordParam];
        String confirmPassword = (String) value[confirmPasswordParam];

        if (password == null || confirmPassword == null) return true; // skip null values

        return password.equals(confirmPassword);
    }
}
