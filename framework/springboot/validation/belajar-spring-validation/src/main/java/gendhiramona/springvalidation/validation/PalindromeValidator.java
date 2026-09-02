package gendhiramona.springvalidation.validation;

import gendhiramona.springvalidation.helper.StringHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class PalindromeValidator implements ConstraintValidator<Palindrome, String> {

    @Autowired
    private StringHelper stringHelper;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return stringHelper.isPalindrome(s);
    }
}
