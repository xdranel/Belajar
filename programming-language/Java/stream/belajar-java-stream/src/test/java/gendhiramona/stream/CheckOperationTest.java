package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

public class CheckOperationTest {

    @Test
    void testAnyMatch() {

        boolean match = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
                .anyMatch(numb -> numb > 5);

        System.out.println(match);
    }

    @Test
    void testAllMatch() {

        boolean match = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
                .allMatch(numb -> numb > 5);

        System.out.println(match);
    }
}
