package gendhiramona.test;

import org.junit.jupiter.api.Test;

public class LifeCycleTest {

    private String temp;

    @Test
    void testA() {
        temp = "John";
    }

    @Test
    void testB() {
        System.out.println(temp);
    }
}
