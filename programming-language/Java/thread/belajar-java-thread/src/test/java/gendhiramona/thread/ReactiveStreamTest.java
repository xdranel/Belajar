package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class ReactiveStreamTest {

    @Test
    void publish() throws InterruptedException {
        var publisher = new SubmissionPublisher<String>();
        var subscriber1 = new PrintSubscriber("A", 1000L);
        var subscriber2 = new PrintSubscriber("B", 500L);
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        var executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(2000);
                    publisher.submit("Data-" + i);
                    System.out.println(Thread.currentThread().getName() + " : Sent Data-" + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(100 * 1000L);
    }

    @Test
    void buffer() throws InterruptedException {
        var publisher = new SubmissionPublisher<String>(Executors.newWorkStealingPool(), 10);
        var subscriber1 = new PrintSubscriber("A", 1000L);
        var subscriber2 = new PrintSubscriber("B", 500L);
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        var executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                publisher.submit("Data-" + i);
                System.out.println(Thread.currentThread().getName() + " : Sent Data-" + i);
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(100 * 1000L);
    }

    @Test
    void processor() throws InterruptedException {

        var publisher = new SubmissionPublisher<String>();

        var processor = new HelloProcessor();
        publisher.subscribe(processor);

        var subscriber1 = new PrintSubscriber("A", 1000L);
        processor.subscribe(subscriber1);

        var executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                publisher.submit("Data-" + i);
                System.out.println(Thread.currentThread().getName() + " : Sent Data-" + i);
            }
        });

        Thread.sleep(100 * 1000L);
    }

    public static class PrintSubscriber implements Flow.Subscriber<String> {

        private Flow.Subscription subscription;

        private final String name;

        private Long sleep;

        public PrintSubscriber(String name, Long sleep) {
            this.name = name;
            this.sleep = sleep;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String s) {
            try {
                Thread.sleep(sleep);
                System.out.println(Thread.currentThread().getName() + " : " + name + " : " + s);
                this.subscription.request(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println(Thread.currentThread().getName() + " : Done");
        }
    }

    public static class HelloProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String s) {
            var value = s.toUpperCase();
            submit(value);
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            close();
        }
    }
}
