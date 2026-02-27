package gendhiramona.reflection;

import gendhiramona.reflection.data.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class FieldTest {

    @Test
    void getField() {

        Class<Person> personClass = Person.class;

        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + " : " + field.getType().getName());
        }
    }

    @Test
    void getFieldValue() throws NoSuchFieldException, IllegalAccessException {

        Class<Person> personClass = Person.class;
        Field firstName = personClass.getDeclaredField("firstName");
        firstName.setAccessible(true);

        Person person1 = new Person("John", "Doe");
        String value1 = (String) firstName.get(person1);
        System.out.println(value1);

        Person person2 = new Person("Sam", "Smith");
        String value2 = (String) firstName.get(person2);
        System.out.println(value2);
    }

    @Test
    void setFieldValue() throws NoSuchFieldException, IllegalAccessException {

        Class<Person> personClass = Person.class;
        Field firstName = personClass.getDeclaredField("firstName");
        firstName.setAccessible(true);

        Person person1 = new Person("John", "Doe");
        firstName.set(person1, "Sam");
        System.out.println(person1.getFirstName());

        Person person2 = new Person("Sam", "Smith");
        firstName.set(person2, "John");
        System.out.println(person2.getFirstName());
    }
}
