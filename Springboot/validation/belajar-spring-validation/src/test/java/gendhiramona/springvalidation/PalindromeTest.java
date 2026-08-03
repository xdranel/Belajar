package gendhiramona.springvalidation;

import gendhiramona.springvalidation.data.Foo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class PalindromeTest {

    @Autowired
    private Validator validator;

    @Test
    void palindromeValid() {
        Set<ConstraintViolation<Foo>> validate = validator.validate(new Foo("madam"));
        Assertions.assertTrue(validate.isEmpty());
    }

    @Test
    void palindromeInvalid() {
        Set<ConstraintViolation<Foo>> validate = validator.validate(new Foo("eko"));
        Assertions.assertFalse(validate.isEmpty());
        Assertions.assertEquals(1, validate.size());
    }

    @Test
    void palindromeInvalidMessage() {
        Set<ConstraintViolation<Foo>> validate = validator.validate(new Foo("eko"));
        Assertions.assertFalse(validate.isEmpty());
        Assertions.assertEquals(1, validate.size());

        String message = validate.stream().findFirst().get().getMessage();
        Assertions.assertEquals("Data is not a palindrome", message);
    }
}
