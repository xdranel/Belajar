package belajar.java.lambda.app;

import java.util.Optional;
import java.util.function.Function;

public class OptionalApp {
    public static void main(String[] args) {

        sayHello("John");

        String name = null;
        sayHello(name);

    }

    // Non-Optional
//    public static void sayHello(String name) {
//        String nameUpper = name.toUpperCase();
//        System.out.println("HELLO " + nameUpper);
//    }

    // Optional
    public static void sayHello(String name) {
        // Not Retrieving Null Value
        Optional.ofNullable(name)
                .map(String::toUpperCase)
                .ifPresent(value -> System.out.println("HELLO " + value)
                );

        // Retrieving Null Value
        Optional.ofNullable(name)
                .map(String::toUpperCase)
                .ifPresentOrElse(
                        value -> System.out.println("HELLO " + value),
                        () -> System.out.println("HELLO ")
                );

        // Retrieving Null Value and Change its Value
        String upperName = Optional.ofNullable(name)
                .map(String::toUpperCase)
                .orElse("FRIENDS");
        System.out.println("HELLO " + upperName);

//        Optional<String> optName = Optional.ofNullable(name);
//        Optional<String> optNameUpper = optName.map(value -> value.toUpperCase());
//        Optional<String> optNameUpper = optName.map(String::toUpperCase);
//        optNameUpper.ifPresent(value -> System.out.println("HELLO " + value));
//        optNameUpper.ifPresent(System.out::println);
    }
}
