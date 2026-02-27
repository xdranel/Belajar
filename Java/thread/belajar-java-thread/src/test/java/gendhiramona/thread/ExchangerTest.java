package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

    @Test
    void test() throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> {
            try {
                System.out.println("Thread 1 Send");
                Thread.sleep(1_000L);
                String string = exchanger.exchange("Hello You");
                System.out.println("Thread 1 Receive: " + string);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                System.out.println("Thread 2 Send");
                Thread.sleep(2_000L);
                String string = exchanger.exchange("Hello There");
                System.out.println("Thread 2 Receive: " + string);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
