package com.gendhiramona.belajar.spring.config.configurationproperties;

import com.gendhiramona.belajar.spring.config.converter.StringToDateConverter;
import com.gendhiramona.belajar.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties applicationProperties;
    
    @Autowired
    private ConversionService conversionService;

    @Test
    void testConfigurationProperties() {
        Assertions.assertEquals("belajar-spring-config-properties", applicationProperties.getName());
        Assertions.assertEquals("1.0.0", applicationProperties.getVersion());
        Assertions.assertFalse(applicationProperties.isProductionMode());
    }

    @Test
    void testDatabaseProperties() {
        Assertions.assertEquals("root", applicationProperties.getDatabase().getUsername());
        Assertions.assertEquals("secret", applicationProperties.getDatabase().getPassword());
        Assertions.assertEquals("belajar", applicationProperties.getDatabase().getDatabase());
        Assertions.assertEquals("jdbc:mysql://localhost:3306/belajar", applicationProperties.getDatabase().getUrl());
    }

    @Test
    void testCollection() {
        Assertions.assertEquals(Arrays.asList("products", "customers", "categories"), applicationProperties.getDatabase().getWhitelistTables());
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTablesSize().get("products"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTablesSize().get("customers"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTablesSize().get("categories"));
    }

    @Test
    void testEmbeddedCollection() {
        Assertions.assertEquals("default", applicationProperties.getRole().getDefaultRoles().get(0).getId());
        Assertions.assertEquals("Default Role", applicationProperties.getRole().getDefaultRoles().get(0).getName());
        Assertions.assertEquals("guest", applicationProperties.getRole().getDefaultRoles().get(1).getId());
        Assertions.assertEquals("Guest Role", applicationProperties.getRole().getDefaultRoles().get(1).getName());

        Assertions.assertEquals("admin", applicationProperties.getRole().getRoles().get("admin").getId());
        Assertions.assertEquals("Admin Role", applicationProperties.getRole().getRoles().get("admin").getName());
        Assertions.assertEquals("finance", applicationProperties.getRole().getRoles().get("finance").getId());
        Assertions.assertEquals("Finance Role", applicationProperties.getRole().getRoles().get("finance").getName());
    }

    @Test
    void testDuration() {
        Assertions.assertEquals(Duration.ofSeconds(10), applicationProperties.getDefaultTimeout());
    }

    @Test
    void testCustomConverter() {
        Date expireDate = applicationProperties.getExpireDate();

        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Assertions.assertEquals("2026-01-01", dateFormat.format(expireDate));
    }

    @Test
    void testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
        Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));

        Duration duration = conversionService.convert("10s", Duration.class);
        Assertions.assertEquals(Duration.ofSeconds(10), duration);
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class,
    })
    @Import({
            StringToDateConverter.class
    })
    public static class TestApplication {

        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
            ApplicationConversionService conversionService = new ApplicationConversionService();
            conversionService.addConverter(stringToDateConverter);
            return conversionService;
        }

    }
}
