package gendhiramona.reflection.validation;

import gendhiramona.reflection.annotation.NotBlank;

import java.lang.reflect.Field;

public class Validator {

    public static void validateNotBlank(Object object) throws IllegalAccessException {

        // get class
        Class<?> aClass = object.getClass();

        // get all fields
        Field[] fields = aClass.getDeclaredFields();

        // iterate each field
        for (Field field : fields) {

            // make field accessible
            field.setAccessible(true);

            // get @NotBlank annotation
            NotBlank notBlank = field.getAnnotation(NotBlank.class);

            // check if @NotBlank is present
            if (notBlank != null) {

                // get field value
                String value = (String) field.get(object);

                if (notBlank.allowEmptyString()) {
                    // allowed empty string
                    // ignore
                } else {
                    // not allowed empty string
                    value = value.trim();
                }

                // check if value is empty string
                if ("".equals(value) || value == null) {
                    throw new RuntimeException("Field: " + field.getName() + " cannot be blank");
                }
            }
        }
    }
}
