package gendhiramona.stream;

import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class PrimitiveStreamTest {

    @Test
    void testCreate() {

        IntStream intStream = IntStream.range(1, 100);
        intStream.forEach(System.out::println);

        LongStream longStream = LongStream.range(1, 100);
        longStream.forEach(System.out::println);

        DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0);
        doubleStream.forEach(System.out::println);
    }

    @Test
    void testOperations() {
        IntStream intStream = IntStream.range(1, 100);
        OptionalDouble optionalDouble = intStream.average();
        optionalDouble.ifPresent(System.out::println);

        IntStream.range(1, 100)
                .mapToObj(number -> "Number: " + number)
                .forEach(System.out::println);
    }
}
