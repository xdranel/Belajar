package gendhiramona.spring.core;

import gendhiramona.spring.core.listener.LoginSuccessListener;
import gendhiramona.spring.core.listener.UserListener;
import gendhiramona.spring.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class EventListenerTest {

    @Configuration
    @Import({
            LoginSuccessListener.class,
            UserService.class,
            UserListener.class,
    })
    public static class TestConfiguration{

    }

    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
        applicationContext.registerShutdownHook();
    }

    @Test
    void testEventListener() {
        UserService userService = applicationContext.getBean(UserService.class);
        userService.login("admin", "admin");
        userService.login("admin", "wrong");
        userService.login("wrong", "wrong");
    }
}
