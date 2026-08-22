package gendhiramona.validation;

import org.junit.jupiter.api.Test;

public class ClassLevelValidationTest extends AbstractValidatorTest{

    @Test
    void testClassLevel() {

        Register register = new Register();
        register.setUsername("gendhiramona");
        register.setPassword("rahasia");
        register.setConfirmPassword("rahasia123");

        validate(register);
    }
}
