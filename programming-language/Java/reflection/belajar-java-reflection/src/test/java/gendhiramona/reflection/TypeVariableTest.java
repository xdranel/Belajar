package gendhiramona.reflection;

import gendhiramona.reflection.data.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class TypeVariableTest {

    @Test
    void testGetTypeVariable() {

        Class<Data> dataClass = Data.class;

        TypeVariable<Class<Data>>[] typeVariables = dataClass.getTypeParameters();

        for (TypeVariable<Class<Data>> typeVariable : typeVariables) {
            System.out.println(typeVariable.getName());
            System.out.println(Arrays.toString(typeVariable.getBounds()));
            System.out.println(typeVariable.getGenericDeclaration());
            System.out.println("====================");
        }
    }
}
