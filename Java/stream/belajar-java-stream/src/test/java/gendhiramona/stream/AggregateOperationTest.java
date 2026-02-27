package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class AggregateOperationTest {

    @Test
    void testMax() {
        List.of("John", "Jane", "Smith", "Jack", "Bob", "Jill", "Jeff").stream()
                .max(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }

    @Test
    void testMin() {
        List.of("John", "Jane", "Smith", "Jack", "Bob", "Jill", "Jeff").stream()
                .min(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }

    @Test
    void testCount() {
        long count = List.of("John", "Jane", "Smith", "Jack", "Bob", "Jill", "Jeff").stream()
                .count();
        System.out.println(count);
    }

    @Test
    void testSum() {
        Integer reduce = List.of(1, 2, 3, 4, 5).stream()
                .reduce(0, (value, item) -> value + item);
//                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    @Test
    void testFactorial() {
        Integer reduce = List.of(1, 2, 3, 4, 5).stream()
                .reduce(1, (value, item) -> value * item);

        System.out.println(reduce);
    }
}
