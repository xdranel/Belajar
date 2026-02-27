package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatterTest {

    @Test
    void parsing() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");

        LocalDate localDate = LocalDate.parse("2020 01 01", formatter);
        System.out.println(localDate);
    }

    @Test
    void format() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");

        LocalDate localDate = LocalDate.parse("2020 01 01", formatter);
        String format = localDate.format(formatter);

        System.out.println(format);
    }

    @Test
    void defaultFormatter() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");

        LocalDate localDate = LocalDate.parse("2020 01 01", formatter);
        String format = localDate.format(DateTimeFormatter.ISO_DATE);

        System.out.println(format);
    }

    @Test
    void i18n() {

        Locale locale = new Locale.Builder()
                .setLanguage("id")
                .setRegion("ID")
                .build();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd, HH:mm:ss", locale);

        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(formatter);

        System.out.println(format);
    }
}
