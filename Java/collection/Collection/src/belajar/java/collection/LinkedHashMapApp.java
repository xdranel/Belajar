package belajar.java.collection;

import java.util.*;

public class LinkedHashMapApp {
    public static void main(String[] args) {

        Map<String, String> map = new LinkedHashMap<>();
//        Map<String, String> map = new HashMap<>();

        map.put("Name", "John");
        map.put("Surname", "Doe");
        map.put("Country", "USA");

        Set<String> keys = map.keySet();
        System.out.println(keys);
        for (String key : keys) {
            System.out.println(key);
        }

        Collection<String> values = map.values();
        System.out.println(values);
        for (String value : values) {
            System.out.println(value);
        }
    }
}
