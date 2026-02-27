package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.*;

public class YearTest {

    @Test
    void create() {

        Year year1 = Year.now();
        Year year2 = Year.of(2020);
        Year year3 = Year.parse("2020");
        System.out.println(year1);
        System.out.println(year2);
        System.out.println(year3);

        YearMonth yearMonth1 = YearMonth.now();
        YearMonth yearMonth2 = YearMonth.of(2020, 1);
        YearMonth yearMonth3 = YearMonth.parse("2020-01");
        System.out.println(yearMonth1);
        System.out.println(yearMonth2);
        System.out.println(yearMonth3);

        MonthDay monthDay1 = MonthDay.now();
        MonthDay monthDay2 = MonthDay.of(1, 1);
        MonthDay monthDay3 = MonthDay.parse("--01-01");
        System.out.println(monthDay1);
        System.out.println(monthDay2);
        System.out.println(monthDay3);
    }

    @Test
    void conversionLocalDate() {

        Year year = Year.now();
        System.out.println(year);

        LocalDate localDate = year.atMonth(Month.JUNE).atDay(14);
        System.out.println(localDate);

        MonthDay monthDay = MonthDay.from(localDate);
        System.out.println(monthDay);
    }

    @Test
    void get() {

        Year year = Year.now();
        YearMonth yearMonth = YearMonth.now();
        MonthDay monthDay = MonthDay.now();

        System.out.println(year.getValue());
        System.out.println(yearMonth.getYear());

        System.out.println(yearMonth.getMonth());
        System.out.println(monthDay.getMonth());

        System.out.println(yearMonth.getMonthValue());
        System.out.println(monthDay.getMonthValue());

        System.out.println(monthDay.getDayOfMonth());
    }
}
