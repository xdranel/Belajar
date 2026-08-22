package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class CreateStreamBuilderTest {

    @Test
    void testCreateStreamBuilder() {

        Stream.Builder<String> builder = Stream.builder();
        builder.accept("John");
        builder.add("Smith").add("Jane");

        Stream<String> stream = builder.build();
        stream.forEach(System.out::println);
    }

    @Test
    void testCreateStreamBuilderSimplify() {

        Stream<Object> build = Stream.builder()
                .add("John").add("Smith").add("Jane")
                .build();

        build.forEach(System.out::println);
    }
}
