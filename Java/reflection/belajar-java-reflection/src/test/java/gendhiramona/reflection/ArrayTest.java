package gendhiramona.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayTest {

    @Test
    void testArrayClass() {

        Class<String[]> stringArrayClass = String[].class;
        Class<int[]> integerArrayClass = int[].class;

        System.out.println(stringArrayClass.isArray());
        System.out.println(integerArrayClass.isArray());
    }

    @Test
    void testArrayMember() {

        Class<String[]> stringArrayClass = String[].class;

        System.out.println(Arrays.toString(stringArrayClass.getDeclaredFields()));
        System.out.println(Arrays.toString(stringArrayClass.getDeclaredMethods()));
        System.out.println(Arrays.toString(stringArrayClass.getDeclaredConstructors()));
        System.out.println(stringArrayClass.getComponentType());
    }

    @Test
    void testArrayManipulation() {

        Class<String[]> stringArrayClass = String[].class;
        String[] array = (String[]) Array.newInstance(stringArrayClass.getComponentType(), 10);

        System.out.println(Arrays.toString(array));

        Array.set(array, 0, "John");
        Array.set(array, 1, "Doe");
        Array.set(array, 2, "Smith");

        System.out.println(Arrays.toString(array));

        System.out.println(Array.get(array, 0));
        System.out.println(Array.get(array, 1));
        System.out.println(Array.get(array, 2));
    }
}
