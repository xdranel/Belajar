package belajar.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionClassApp {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.addAll(List.of("John", "Sam", "Daniel", "Santa", "Michael", "Clarissa", "Francis"));
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);
    }
}
