package belajar.java.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionApp {
    public static void main(String[] args) {

        Collection<String> names = new ArrayList<>();
        names.add("Felix");
        names.add("John");
        names.add("Daniel");
        names.addAll(List.of("Arthur", "Sam", "Bob"));
        names.add("Logan");

        for (String name : names) {
            System.out.println(name);
        }
//
//        System.out.println("BREAK LINE");
//
//        names.remove("Daniel");
//        names.removeAll(List.of("Arthur", "Bob"));
//        for (String name : names) {
//            System.out.println(name);
//        }
//
//        System.out.println("BREAK LINE");
//
//        System.out.println(
//                names.contains("Daniel")
//        );
//
//        System.out.println(
//                names.containsAll(List.of("John", "Sam"))
//        );
//
//        System.out.println(names.size());
    }
}
