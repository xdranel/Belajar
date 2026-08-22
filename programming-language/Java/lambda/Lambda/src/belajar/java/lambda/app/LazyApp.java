package belajar.java.lambda.app;

import java.util.function.Supplier;

public class LazyApp {
    public static void main(String[] args) {
//        testScore(50, getName());

        testScore(50, () -> getName());
    }

    // Not Lazy
//    public static void testScore(int score, String name) {
//        if (score > 80) {
//            System.out.println("Congratulations!" + name + " your score is " + score);
//        } else {
//            System.out.println("Good Luck Next Time!");
//        }
//    }

    // Lazy
    public static void testScore(int score, Supplier<String> name) {
        if (score > 80) {
            System.out.println("Congratulations!" + name.get() + " your score is " + score);
        } else {
            System.out.println("Good Luck Next Time!");
        }
    }

    public static String getName() {
        System.out.println("Method getName() called");
        return "John";
    }
}
