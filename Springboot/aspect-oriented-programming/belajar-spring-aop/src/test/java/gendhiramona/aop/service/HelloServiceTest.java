package gendhiramona.aop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceTest {
    
    @Autowired
    private HelloService helloService;

    @Test
    void testService() {
        Assertions.assertEquals("Hello Gendhi", helloService.hello("Gendhi"));
        Assertions.assertEquals("Hello Gendhi Ramona", helloService.hello("Gendhi", "Ramona"));
        Assertions.assertEquals("Bye Gendhi", helloService.bye("Gendhi"));

        helloService.test();
    }
}
