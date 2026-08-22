package gendhiramona.datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;

public class TemporalTest {

    @Test
    void temporal() {

        Temporal temporal1 = LocalDate.now();
        Temporal temporal2 = LocalTime.now();
        Temporal temporal3 = ZonedDateTime.now();
        Temporal temporal4 = Year.now();
    }

    @Test
    void temporalAmount() {

        Temporal temporal1 = LocalDateTime.now();
        Temporal temporal2 = temporal1.plus(Duration.ofHours(10));
        Temporal temporal3 = temporal1.plus(Duration.ofDays(10).plusHours(15));

        System.out.println(temporal1);
        System.out.println(temporal2);
        System.out.println(temporal3);
    }

    @Test
    void temporalUnit() {

        long temporalUnit1 = ChronoUnit.MINUTES.between(LocalTime.now(), LocalTime.now().plusHours(2));
        System.out.println(temporalUnit1);
    }

    @Test
    void temporalField() {

        Temporal temporal1 = LocalDateTime.now();
        System.out.println(temporal1.get(ChronoField.DAY_OF_MONTH));
        System.out.println(temporal1.get(ChronoField.DAY_OF_WEEK));
    }

    @Test
    void temporalQuery() {

        Temporal temporal = LocalDateTime.now();

        List<Integer> integers = temporal.query(new TemporalQuery<List<Integer>>() {
            @Override
            public List<Integer> queryFrom(TemporalAccessor temporalAccessor) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(temporalAccessor.get(ChronoField.YEAR));
                list.add(temporalAccessor.get(ChronoField.MONTH_OF_YEAR));
                list.add(temporalAccessor.get(ChronoField.DAY_OF_MONTH));
                return list;
            }
        });

        System.out.println(integers);
    }

    @Test
    void temporalAdjuster() {

        Temporal temporal1 = LocalDate.now();
        System.out.println(temporal1);

        Temporal temporal2 = temporal1.with(new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                return LocalDate.of(
                        temporal.get(ChronoField.YEAR),
                        temporal.get(ChronoField.MONTH_OF_YEAR),
                        1
                );
            }
        });
        System.out.println(temporal2);

        Temporal temporal3 = temporal1.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(temporal3);

        Temporal temporal4 = temporal1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(temporal4);
    }
}
