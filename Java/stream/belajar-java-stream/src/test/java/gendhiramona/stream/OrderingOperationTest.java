package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class OrderingOperationTest {

    @Test
    void testSorted() {
        List.of("John", "Jane", "Bob", "Jacky", "Rommy").stream()
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void testSortedWithComparator() {
        Comparator<String> comparator = Comparator.comparing((String s) -> s.length()).reversed();
//        Comparator<String> comparator = Comparator.comparing(String::length).reversed();
//        Comparator<String> comparator = Comparator.reverseOrder();

        List.of("John", "Jane", "Bob", "Jacky", "Rommy").stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}
