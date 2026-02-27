package gendhiramona.reflection;

import gendhiramona.reflection.data.Gender;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EnumTest {

    @Test
    void testEnum() {

        Class<Gender> genderClass = Gender.class;

        System.out.println(genderClass.getName());
        System.out.println(genderClass.isEnum());
        System.out.println(genderClass.getSuperclass());
        System.out.println(Arrays.toString(genderClass.getEnumConstants()));
        System.out.println(Arrays.toString(genderClass.getDeclaredFields()));
        System.out.println(Arrays.toString(genderClass.getDeclaredMethods()));
        System.out.println(Arrays.toString(genderClass.getDeclaredConstructors()));
    }
}
