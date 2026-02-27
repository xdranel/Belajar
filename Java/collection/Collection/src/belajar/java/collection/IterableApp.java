package belajar.java.collection;

import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.List;

public class IterableApp {
    public static void main(String[] args) {

        Iterable<String> names = List.of("Felix", "John", "Daniel");

        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("ITERATOR");
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
