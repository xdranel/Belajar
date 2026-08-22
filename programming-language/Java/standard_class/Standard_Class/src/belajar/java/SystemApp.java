package belajar.java;

import java.util.Map;

public class SystemApp {
    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());

        System.out.println(System.getenv("HOME"));
        System.out.println(System.getenv("BELAJAR_APP"));

        System.gc();
        System.exit(1);

        System.out.println("Hi");
    }
}
