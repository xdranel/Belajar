package com.gendhiramona.belajar.spring.config.propertysource;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = PropertySourceTest.TestApplication.class)
public class PropertySourceTest {

    @Autowired
    private TestApplication.SampleProperties sampleProperties;

    @Test
    void testPropertySource() {
        Assertions.assertEquals("sample project", sampleProperties.getAppName());
        Assertions.assertEquals("1.0.0", sampleProperties.getAppVersion());
    }

    @SpringBootApplication
    @PropertySources({
            @PropertySource("classpath:/sample.properties"),
    })
    public static class TestApplication {

        @Component
        @Getter
        public static class SampleProperties {

            @Value("${sample.name}")
            private String appName;

            @Value("${sample.version}")
            private String appVersion;
        }
    }
}
