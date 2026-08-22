package com.gendhiramona.belajar.spring.config.profileproperties;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfilePropertiesTest.TestApplication.class)
@ActiveProfiles({"production", "test"})
public class ProfilePropertiesTest {
    
    @Autowired
    private TestApplication.ProfileProperties profileProperties;

    @Test
    void testNameProfileProperties() {
        Assertions.assertEquals("Default", profileProperties.getDefaultFile());
        Assertions.assertEquals("Production", profileProperties.getProductionFile());
        Assertions.assertEquals("Test", profileProperties.getTestFile());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        @Getter
        public static class ProfileProperties {

            @Value("${profile.default}")
            private String defaultFile;

            @Value("${profile.production}")
            private String productionFile;

            @Value("${profile.test}")
            private String testFile;

        }

    }
}
