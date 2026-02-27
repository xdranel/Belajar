package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimeTest {

    @Test
    void create() {

        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, Month.JUNE, 14, 4, 30, 0, 0);
        LocalDateTime LocalDateTime3 = LocalDateTime.parse("2020-01-01T12:30");

        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(LocalDateTime3);
    }

    @Test
    void with() {
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = localDateTime1.withYear(2020).withMonth(12);
        LocalDateTime localDateTime3 = localDateTime1.withYear(2001).withMonth(6).withDayOfMonth(14);

        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
    }

    @Test
    void modify() {

        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = localDateTime1.plusYears(10).plusMonths(10);
        LocalDateTime localDateTime3 = localDateTime1.minusMonths(20).minusDays(10).minusHours(10).minusMinutes(30);

        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
    }

    @Test
    void get() {
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1.getYear());
        System.out.println(localDateTime1.getMonth());
        System.out.println(localDateTime1.getMonthValue());
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getDayOfWeek());
        System.out.println(localDateTime1.getDayOfYear());
        System.out.println(localDateTime1.getHour());
        System.out.println(localDateTime1.getMinute());
    }

    @Test
    void date() {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);

        LocalDateTime result = localDate.atTime(localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        System.out.println(result);
    }

    @Test
    void time() {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);

        LocalDateTime result = localTime.atDate(LocalDate.now());
        System.out.println(result);

    }
}
