package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    void parallel() {

        Stream<Integer> stream = IntStream.range(0, 1000).boxed();

        stream.parallel()
                .map(i -> i * 2)
                .forEach(integer -> {
                    System.out.println(Thread.currentThread().getName() + " : " + integer);
                });
    }

    @Test
    void customPool() {
//        var pool = ForkJoinPool.commonPool();
        var pool = new ForkJoinPool(6);
        var task = pool.submit(() -> {
            Stream<Integer> stream = IntStream.range(0, 1000).boxed();

            stream.parallel()
                    .map(i -> i * 2)
                    .forEach(integer -> {
                        System.out.println(Thread.currentThread().getName() + " : " + integer);
                    });
        });

        task.join();
    }
}
