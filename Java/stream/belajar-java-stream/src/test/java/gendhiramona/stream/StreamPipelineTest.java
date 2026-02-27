package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

public class StreamPipelineTest {

    @Test
    void testStreamPipeline() {

        List.of("John", "Jake", "Smith").stream()
                .map(name -> name.toUpperCase())
                .map(upper -> "Mr. " + upper)
                .forEach(System.out::println);
    }
}
