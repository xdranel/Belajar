package gendhiramona.springvalidation;

import gendhiramona.springvalidation.data.Person;
import gendhiramona.springvalidation.helper.SayHello;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@SpringBootTest
@TestPropertySources({
        @TestPropertySource("classpath:/test.properties")
})
public class SayHelloTest {

    @Autowired
    private SayHello sayHello;

    @Test
    void testSuccess() {
        Person person = new Person("1", "Gendhi");
        String hello = sayHello.sayHello(person);
        Assertions.assertEquals("Hello Gendhi", hello);
    }

    @Test
    void testFail() {
        Person person = new Person("", "");
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            sayHello.sayHello(person);
        });
    }
}
