package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class CreateStreamTest {

    @Test
    void testCreateEmptySingleStream() {

        Stream<String> emptyStream = Stream.empty();
        emptyStream.forEach(data -> System.out.println(data));

        Stream<String> singleStream = Stream.of("John");
        singleStream.forEach(data -> System.out.println(data));

        String data = "Jake";
        Stream<String> emptyOrNotStream = Stream.ofNullable(data);
        emptyOrNotStream.forEach(item -> System.out.println(item));
    }

    @Test
    void testCreateStreamArray() {
        Stream<String> stringStream = Stream.of("John", "Jane", "Jack");
        stringStream.forEach(System.out::println);

        String[] array = new String[]{
                "John", "Jane", "Jack"
        };
        Stream<String> arrayStream = Arrays.stream(array);
        arrayStream.forEach(System.out::println);
    }

    @Test
    void testCreateStreamFromCollection() {
        Collection<String> collection = List.of("John", "Jane", "Jack");
        Stream<String> stringStream = collection.stream();
        stringStream.forEach(System.out::println);

        Stream<String> stringStream2 = collection.stream();
        stringStream2.forEach(System.out::println);
    }

    @Test
    // Careful
    void testCreateInfiniteStream() {
        Stream<String> infiniteStream = Stream.generate(() -> "John");
//        infiniteStream.forEach(System.out::println);

        Stream<Integer> iterate = Stream.iterate(1, value -> value + 1);
//        iterate.forEach(System.out::println);
    }
}
