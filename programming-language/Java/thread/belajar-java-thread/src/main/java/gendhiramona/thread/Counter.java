package gendhiramona.thread;

public class Counter {

    // Race Condition - RaceConditionTest
    private Long value = 0L;

    public void increment() {
        value++;
    }

    public Long getValue() {
        return value;
    }
}
