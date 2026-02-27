package belajar.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingApp {
    public static void main(String[] args) {

        Comparator<String> reverse = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        };

        List<String> list = new ArrayList<>();

        list.addAll(List.of("John", "Amelia", "Sam", "Bob", "Daniel"));

        Collections.sort(list);
        System.out.println(list);
        for (var value : list) {
            System.out.println(value);
        }

        Collections.sort(list, reverse);
        System.out.println(list);
        for (var value : list) {
            System.out.println(value);
        }
    }
}
