package belajar.java;

import java.util.Random;

public class RandomApp {
    public static void main(String[] args) {

        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int value = rand.nextInt(1000);
            System.out.println(value);
        }
    }
}
