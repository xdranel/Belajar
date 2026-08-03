package gendhiramona.spring.core.application;

import gendhiramona.spring.core.data.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FooApplication.class)
class FooApplicationTest {

    @Autowired
    Foo foo;

    @Test
    void testSpringBoot() {
        Assertions.assertNotNull(foo);
    }
}