package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class TransformationOperationTest {

    @Test
    void testMap() {
        List.of("John", "Jane", "Smith").stream()
                .map(name -> name.toUpperCase())
                .map(upperName -> upperName.length())
                .forEach(length -> System.out.println(length));
    }

    @Test
    void testFlatMap() {
        List.of("John", "Jane", "Smith").stream()
                .map(name -> name.toUpperCase())
                .flatMap(upperName -> Stream.of(upperName, upperName.length()))
                .forEach(upperName -> System.out.println(upperName));
    }
}
