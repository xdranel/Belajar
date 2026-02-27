package gendhiramona.reflection;

import gendhiramona.reflection.data.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodTest {

    @Test
    void getMethods() {

        Class<Person> personClass = Person.class;

        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
            System.out.println(method.getReturnType().getName());
            System.out.println(Arrays.toString(method.getParameterTypes()));
            System.out.println(Arrays.toString(method.getExceptionTypes()));
            System.out.println("====================");
        }
    }

    @Test
    void getMethodsValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<Person> personClass = Person.class;
        Method getFirstName = personClass.getDeclaredMethod("getFirstName");

        Person person = new Person("John", "Doe");
        String firstName = (String) getFirstName.invoke(person);
        System.out.println(firstName);
    }
}
