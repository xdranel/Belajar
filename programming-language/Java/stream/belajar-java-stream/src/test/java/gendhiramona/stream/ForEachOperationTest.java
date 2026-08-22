package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ForEachOperationTest {

    @Test
    void testPeek() {
        List.of("John", "Jake", "Jack", "Jill", "Jeff").stream()
                .peek(name -> System.out.println("Before: " + name))
                .map(String::toUpperCase)
                .peek(upper -> System.out.println("After: " + upper))
                .forEach(System.out::println);
    }
}
