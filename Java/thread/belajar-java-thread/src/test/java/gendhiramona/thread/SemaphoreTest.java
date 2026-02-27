package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class SemaphoreTest {

    @Test
    void test() throws InterruptedException {

        final var semaphore = new Semaphore(10);
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000L);
                    System.out.println("Task " + finalI + " Finished");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
