package gendhiramona.thread;

public class SynchronizedCounter {

    // Race Condition - SynchronizationTest
    private Long value = 0L;

    public void increment() {
        // Rest of the code

        // To make only part of the code synchronized
        // What i learn is that this block of synchronized doing lock and unlock so that way
        // it will had nano delay
        synchronized (this) {
            value++;
        }
        // Rest of the code
    }

    public Long getValue() {
        return value;
    }
}
