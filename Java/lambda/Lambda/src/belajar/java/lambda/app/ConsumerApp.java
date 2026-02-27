package belajar.java.lambda.app;

import java.util.function.Consumer;

public class ConsumerApp {
    public static void main(String[] args) {

//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println("Consumer : " + s);
//            }
//        };
//        consumer.accept("John");

        Consumer<String> consumer = name -> System.out.println("Hello " + name);
    }
}
