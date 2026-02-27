package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateTest {

    @Test
    void create() {

        var date1 = new Date();
        var date2 = new Date(System.currentTimeMillis());
        var date3 = new Date(1577836800000L);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
    }
}
