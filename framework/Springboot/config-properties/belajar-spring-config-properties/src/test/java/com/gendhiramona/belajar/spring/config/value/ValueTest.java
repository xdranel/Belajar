package com.gendhiramona.belajar.spring.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

    @Autowired
    private TestApplication.ApplicationProperties applicationProperties;

    @Autowired
    private TestApplication.SystemProperties systemProperties;

    @Test
    void testValue() {
        Assertions.assertEquals("belajar-spring-config-properties", applicationProperties.getName());
        Assertions.assertEquals("1.0.0", applicationProperties.getVersion());
        Assertions.assertFalse(applicationProperties.isProductionMode());
    }

    @Test
    void testSystemProperties() {
        Assertions.assertNotNull(systemProperties.getJavaHome());
//        Assertions.assertEquals("");
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        @Getter
        public static class SystemProperties {
            @Value("${java.home}")
            private String javaHome;
        }

        @Component
        @Getter
        public static class ApplicationProperties {

            @Value("${application.name}")
            private String name;

            @Value("${application.version}")
            private String version;

            @Value("${application.production-mode}")
            private boolean productionMode;
        }

    }
}
