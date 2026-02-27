package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateTest {

    @Test
    void create() {

        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2020, 1, 1);
        LocalDate localDate3 = LocalDate.parse("1990-06-14");

        System.out.println(localDate1);
        System.out.println(localDate2);
        System.out.println(localDate3);
    }

    @Test
    void with() {

        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = localDate1.withYear(1990);
        LocalDate localDate3 = localDate1.withYear(2001).withMonth(6).withDayOfMonth(14);

        System.out.println(localDate1);
        System.out.println(localDate2);
        System.out.println(localDate3);
    }

    @Test
    void modify() {
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = localDate1.plusYears(10);
        LocalDate localDate3 = localDate1.minusMonths(20);

        System.out.println(localDate1);
        System.out.println(localDate2);
        System.out.println(localDate3);
    }

    @Test
    void get() {
        LocalDate localDate1 = LocalDate.now();
        System.out.println(localDate1.getYear());
        System.out.println(localDate1.getMonth());
        System.out.println(localDate1.getMonthValue());
        System.out.println(localDate1.getDayOfMonth());
        System.out.println(localDate1.getDayOfWeek());
        System.out.println(localDate1.getDayOfYear());
    }
}
