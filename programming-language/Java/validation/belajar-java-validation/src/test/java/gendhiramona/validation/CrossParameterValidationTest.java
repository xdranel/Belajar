package gendhiramona.validation;

import gendhiramona.validation.service.UserService;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Set;

public class CrossParameterValidationTest extends AbstractValidatorTest {

    @Test
    void testCrossParameter() throws NoSuchMethodException {

        UserService userService = new UserService();

        Method method = UserService.class.getMethod("register", String.class, String.class, String.class);
        String username = "johndoe";
        String password = "rahasia";
        String confirmPassword = "rahasia123";

        Set<ConstraintViolation<UserService>> violations = executableValidator
                .validateParameters(userService, method, new Object[]{
                        username, password, confirmPassword
                });

        for (ConstraintViolation<UserService> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("=====================================");
        }
    }
}
