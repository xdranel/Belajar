package belajar.java.lambda.app;

import java.util.List;
import java.util.function.Consumer;

public class ForEachApp {
    public static void main(String[] args) {

        List<String> names = List.of("John", "Jane", "Bob", "Jack");

        // for loop
        for (var name : names) {
            System.out.println(name);
        }

        // forEach anonymous class
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // only printing
        // lambda(rarely)
        Consumer<String> namesLambda = value -> System.out.println(value);
        // lambda(usually)
        names.forEach(value-> System.out.println(value));

        // lambda method reference
        names.forEach(System.out::println);
    }
}
