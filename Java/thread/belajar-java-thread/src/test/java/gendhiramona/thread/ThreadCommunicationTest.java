package gendhiramona.thread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;

    @Test
    void manual() throws InterruptedException {

        var thread1 = new Thread(() -> {
            while (message == null) {
                // wait
            }
            System.out.println("Message received: " + message);
        });

        var thread2 = new Thread(() -> {
            message = "Hello World";
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    void waitNotify() throws InterruptedException {

        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("Message received: " + message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock) {
                message = "Hello World";
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    void waitNotifyAll() throws InterruptedException {

        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("Message received: " + message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var thread3 = new Thread(() -> {
           synchronized (lock) {
               try {
                   lock.wait();
                   System.out.println("Message received: " + message);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock) {
                message = "Hello World";
                lock.notifyAll();
            }
        });

        thread1.start();
        thread3.start();

        thread2.start();

        thread1.join();
        thread3.join();

        thread2.join();
    }
}
