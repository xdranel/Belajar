package gendhiramona.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void mainThread() {
        String name = Thread.currentThread().getName();
        System.out.println("Main thread name: " + name);
    }

    @Test
    void createThread() {
        Runnable runnable = () -> {
            System.out.println("Hello from a thread : " + Thread.currentThread().getName());
        };

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Finished");
    }

    @Test
    void threadSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from a thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Finished");
        Thread.sleep(3_000L);
    }

    @Test
    void threadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from a thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Waiting for thread to finish");
        thread.join();
        System.out.println("Program Finished");
    }

    @Test
    void threadInterrupt() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("Waiting for thread to finish");
        thread.join();
        System.out.println("Program Finished");
    }

    @Test
    void threadInterruptCorrect() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                // Manual Checking if the thread has been interrupted
                if (Thread.interrupted()){
                    return;
                }
                System.out.println("Runnable " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("Waiting for thread to finish");
        thread.join();
        System.out.println("Program Finished");
    }

    @Test
    void threadName() {
        var thread = new Thread(() -> {
            System.out.println("Run on thread: " + Thread.currentThread().getName());
        });
        thread.setName("MyThread");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException {
        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run on thread: " + Thread.currentThread().getName());
        });
        thread.setName("MyThread");
        System.out.println(thread.getState());

        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
