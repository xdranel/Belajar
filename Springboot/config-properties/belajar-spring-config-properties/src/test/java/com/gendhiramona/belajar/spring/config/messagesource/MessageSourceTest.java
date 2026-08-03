package com.gendhiramona.belajar.spring.config.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageSourceTest {

    private ApplicationContext applicationContext;

    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    @Test
    void testDefaultLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Gendhi"}, Locale.ENGLISH);
        Assertions.assertEquals("Hello Gendhi", message);
    }

    @Test
    void testIndonesianLocale() {
        //deprecated
        //String message = messageSource.getMessage("hello", new Object[]{"Gendhi"}, new Locale("in", "ID"));

        //java version 19+ can be only language or both language and country
        //factory method approach
        //Recommended for hardcoding
        //String message = messageSource.getMessage("hello", new Object[]{"Gendhi"}, Locale.of("id", "ID"));
        //or
        //Good for Web/API inputs
        //String message = messageSource.getMessage("hello", new Object[]{"Gendhi"}, Locale.forLanguageTag("id-ID"));

        //both above are equivalent only the parameter difference
        //for Locale.of you can use Language and Country like the example
        //for Locale.forLanguageTag you use it like the example
        //but remember for indonesian use "id" since "in" is not a valid language code on newer ISO 639-1 standard
        //so the file have to be set id_ID not in_ID

        //or if the code running inside a Spring Web MVC controller
        //Best for Production Spring Apps

        //Simulate Spring setting the locale for the current thread
        LocaleContextHolder.setLocale(Locale.forLanguageTag("id-ID"));

        String message = messageSource.getMessage("hello", new Object[]{"Gendhi"}, LocaleContextHolder.getLocale());

        Assertions.assertEquals("Halo Gendhi", message);
    }

    @SpringBootApplication
    public static class TestApplication {

        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasenames("my");
            return messageSource;
        }

    }
}
