package belajar.java.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorApp {
    public static void main(String[] args) {

        List<String> list = List.of("A","B","C","D","E","F","G","H","I","J","K","L","M","N");

        Spliterator<String> spliterator1 = list.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();

        System.out.println(spliterator1.estimateSize());
        System.out.println(spliterator2.estimateSize());

        spliterator1.forEachRemaining(new  Consumer<String>() {
            @Override
            public void accept(String string) {
                System.out.println(string);
            }
        });

        spliterator2.forEachRemaining(new  Consumer<String>() {
            @Override
            public void accept(String string) {
                System.out.println(string);
            }
        });
    }
}
