package belajar.java.util;

import belajar.java.annotation.NotBlank;
import belajar.java.data.LoginRequest;
import belajar.java.error.BlankException;
import belajar.java.error.ValidationException;

import java.lang.reflect.Field;

public class ValidationUtil {

    public static void validate(LoginRequest loginRequest) throws ValidationException, NullPointerException {
        if (loginRequest.username() == null || loginRequest.password() == null) {
            throw new NullPointerException("Username and password Can't be null");
        } else if (loginRequest.username().isBlank() || loginRequest.password().isBlank()) {
            throw new ValidationException("Username and password Can't be blank");
        }
    }

    public static void validateRuntime(LoginRequest loginRequest) {
        if (loginRequest.username() == null || loginRequest.password() == null) {
            throw new NullPointerException("Username and password Can't be null");
        } else if (loginRequest.username().isBlank() || loginRequest.password().isBlank()) {
            throw new BlankException("Username and password Can't be blank");
        }
    }

    public static void validationReflection(Object object) {
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (var field : fields) {
            field.setAccessible(true);

            if (field.getAnnotation(NotBlank.class) != null) {
                //Validated
                try {
                    String value = (String) field.get(object);

                    if (value == null || value.isBlank()) {
                        throw new BlankException("Field " + field.getName() + " is blank ");
                    }
                } catch (IllegalAccessException exception) {
                    System.out.println("Can't access field " + field.getName());
                }
            }
        }
    }
}
