package gendhiramona.thread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueTest {

    @Test
    void arrayBlockingQueue() throws InterruptedException {
        final var queue = new ArrayBlockingQueue<String>(5);
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    queue.put("Data " + finalI);
                    System.out.println("Data " + finalI + " added to queue");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000L);
                    var take = queue.take();
                    System.out.println(take + " removed from queue");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void linkedBlockingQueue() throws InterruptedException {
        final var queue = new LinkedBlockingQueue<>();
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    queue.put("Data " + finalI);
                    System.out.println("Data " + finalI + " added to queue");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000L);
                    var take = queue.take();
                    System.out.println(take + " removed from queue");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void priorityBlockingQueue() throws InterruptedException {
        final var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                queue.put(index);
                System.out.println(index + " added to queue");
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000L);
                    var take = queue.take();
                    System.out.println(take + " removed from queue");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void delayedQueue() throws InterruptedException {
        final var queue = new DelayQueue<ScheduledFuture<String>>();
        final var executor = Executors.newFixedThreadPool(20);
        final var executorScheduler = Executors.newScheduledThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            final var index = i;
            queue.put(executorScheduler.schedule(() -> "Data " + index, i, TimeUnit.SECONDS));
        }

        executor.execute(() -> {
            while (true) {
                try {
                    var take = queue.take();
                    System.out.println(take.get() + " removed from queue");
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void synchronousQueue() throws InterruptedException {
        final var queue = new SynchronousQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    queue.put("Data-" + index);
                    System.out.println("Finish Put Data: " + index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000L);
                    var take = queue.take();
                    System.out.println("Received Data: " + take);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void blockingDequeue() throws InterruptedException {
        final var queue = new LinkedBlockingDeque<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            try {
                queue.putLast("Data-" + index);
                System.out.println("Finish Put Data: " + index);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000L);
                    var take = queue.takeLast();
                    System.out.println("Received Data: " + take);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void transferQueue() throws InterruptedException {
        final var queue = new LinkedTransferQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    queue.transfer("Data-" + index);
                    System.out.println("Finish Put Data: " + index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000L);
                    var take = queue.take();
                    System.out.println("Received Data: " + take);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
