package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationTest {

    @Test
    void create() {

        Duration duration1 = Duration.ofHours(10);
        Duration duration2 = Duration.ofMinutes(10);
        Duration duration3 = Duration.ofSeconds(10);

        System.out.println(duration1);
        System.out.println(duration2);
        System.out.println(duration3);
    }

    @Test
    void get() {

        Duration duration = Duration.ofHours(2);

        System.out.println(duration.toMinutes());
        System.out.println(duration.toSeconds());
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());
    }

    @Test
    void between() {

        Duration duration1 = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        System.out.println(duration1.toMinutes());
        System.out.println(duration1.toSeconds());
    }
}
