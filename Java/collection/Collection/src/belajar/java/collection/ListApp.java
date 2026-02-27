package belajar.java.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListApp {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
//        List<String> names = new LinkedList<>();

        names.add("Felix");
        names.add("John");
        names.add("Julie");

        names.set(0, "Arthur");
        System.out.println(names);
        names.remove(1);
        System.out.println(names);
        System.out.println(names.get(1));

        for(String name : names) {
            System.out.println(name);
        }

    }
}
