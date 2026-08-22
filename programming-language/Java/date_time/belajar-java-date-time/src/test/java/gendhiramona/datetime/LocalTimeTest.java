package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class LocalTimeTest {

    @Test
    void create() {

        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(12, 30, 45);
        LocalTime localTime3 = LocalTime.parse("12:30:45");

        System.out.println(localTime1);
        System.out.println(localTime2);
        System.out.println(localTime3);
    }

    @Test
    void change() {

        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = localTime1.withHour(12);
        LocalTime localTime3 = localTime1.withMinute(30);

        System.out.println(localTime1);
        System.out.println(localTime2);
        System.out.println(localTime3);
    }

    @Test
    void modify() {

        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = localTime1.plusHours(10);
        LocalTime localTime3 = localTime1.plusHours(10).minusMinutes(30);

        System.out.println(localTime1);
        System.out.println(localTime2);
        System.out.println(localTime3);
    }

    @Test
    void get() {

        LocalTime localTime1 = LocalTime.now();

        System.out.println(localTime1.getHour());
        System.out.println(localTime1.getMinute());
        System.out.println(localTime1.getSecond());
        System.out.println(localTime1.getNano());
    }
}
