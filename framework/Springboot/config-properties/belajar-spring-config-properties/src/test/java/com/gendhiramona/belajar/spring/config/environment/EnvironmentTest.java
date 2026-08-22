package com.gendhiramona.belajar.spring.config.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = EnvironmentTest.TestApplication.class)
public class EnvironmentTest {

    @Autowired
    private Environment environment;

    @Test
    public void testEnvironment() {
        String javaHome = environment.getProperty("java.home");
//        Assertions.assertEquals("/home/xdranel/.sdkman/candidates/java/current", javaHome);
        System.out.println(javaHome);
    }

    @SpringBootApplication
    public static class TestApplication {

    }
}
