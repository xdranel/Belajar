package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    void testFuture() throws ExecutionException, InterruptedException {

        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(3_000L);
            return "Hello from a callable";
        };
        Future<String> future = executor.submit(callable);
        System.out.println("Submit Future");

        while (!future.isDone()) {
            System.out.println("Waiting for future to finish");
            Thread.sleep(1_000L);
        }

        String string = future.get();
        System.out.println("Result : " + string);
    }

    @Test
    void testFutureCancel() throws ExecutionException, InterruptedException {

        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5_000L);
            return "Hello from a callable";
        };
        Future<String> future = executor.submit(callable);
        System.out.println("Future Finished");

        Thread.sleep(2_000L);
        future.cancel(true);

        System.out.println(future.isCancelled());
        String string = future.get();
        System.out.println("Result : " + string);
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11)
                .mapToObj(value -> (Callable<String>) () -> {
                    Thread.sleep(value * 500L);
                    return String.valueOf(value);
                }).collect(Collectors.toList());

        List<Future<String>> futures = executor.invokeAll(callables);

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11)
                .mapToObj(value -> (Callable<String>) () -> {
                    Thread.sleep(value * 500L);
                    return String.valueOf(value);
                }).collect(Collectors.toList());

        String string = executor.invokeAny(callables);
        System.out.println(string);
    }
}
