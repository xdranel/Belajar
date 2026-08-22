package gendhiramona.reflection;

import gendhiramona.reflection.data.Person;
import org.junit.jupiter.api.Test;

public class SuperClassTest {

    @Test
    void getSuperClass() {

        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        Class<? super Person> superclass1 = personClass.getSuperclass();
        System.out.println(superclass1);

        Class<? super Person> superclass2 = superclass1.getSuperclass();
        System.out.println(superclass2);
    }
}
