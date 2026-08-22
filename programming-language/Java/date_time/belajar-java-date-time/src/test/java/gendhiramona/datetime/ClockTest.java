package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.*;

public class ClockTest {

    @Test
    void create() {

        Clock clock1 = Clock.systemUTC();
        System.out.println(clock1);

        Clock clock2 = Clock.systemDefaultZone();
        System.out.println(clock2);

        Clock clock3 = Clock.system(ZoneId.of("Asia/Jakarta"));
        System.out.println(clock3);
    }

    @Test
    void instant() throws Throwable {

        Clock clock = Clock.system(ZoneId.of("Asia/Jakarta"));

        Instant instant1 = clock.instant();
        System.out.println(instant1);
        Thread.sleep(1_000);

        Instant instant2 = clock.instant();
        System.out.println(instant2);
        Thread.sleep(1_000);
    }

    @Test
    void fromClock() {

        Clock clockJakarta = Clock.system(ZoneId.of("Asia/Jakarta"));

        Year year = Year.now(clockJakarta);
        System.out.println(year);

        YearMonth yearMonth = YearMonth.now(clockJakarta);
        System.out.println(yearMonth);

        MonthDay monthDay = MonthDay.now(clockJakarta);
        System.out.println(monthDay);

        LocalTime localTime = LocalTime.now(clockJakarta);
        System.out.println(localTime);

        LocalDateTime localDateTime = LocalDateTime.now(clockJakarta);
        System.out.println(localDateTime);

        OffsetTime offsetTime = OffsetTime.now(clockJakarta);
        System.out.println(offsetTime);

        OffsetDateTime offsetDateTime = OffsetDateTime.now(clockJakarta);
        System.out.println(offsetDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(clockJakarta);
        System.out.println(zonedDateTime);
    }
}
