package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class RetrievingOperationTest {

    @Test
    void testLimit() {
        List.of("John", "Jane", "Jake", "Jill", "Bob").stream()
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    void testSkip() {
        List.of("John", "Jane", "Jake", "Jill", "Bob").stream()
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    void testTakeWhile() {
        List.of("John", "Janella", "Jacky", "Jill", "Bob").stream()
                .takeWhile(name -> name.length() <= 4)
                .forEach(System.out::println);
    }

    @Test
    void testDropWhile() {
        List.of("John", "Janella", "Jacky", "Jill", "Bob").stream()
                .dropWhile(name -> name.length() <= 4)
                .forEach(System.out::println);
    }

    @Test
    void testFindAny() {
        Optional<String> optional = List.of("John", "Janella", "Jacky", "Jill", "Bob").stream()
                .findAny();

        optional.ifPresent(System.out::println);
    }

    @Test
    void testFindFirst() {
        Optional<String> optional = List.of("John", "Janella", "Jacky", "Jill", "Bob").stream()
                .findFirst();

        optional.ifPresent(System.out::println);
    }
}
