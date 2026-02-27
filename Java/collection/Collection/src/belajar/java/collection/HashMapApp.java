package belajar.java.collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapApp {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        map.put("age", "25");
        map.put("country", "North America");

        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
        System.out.println(map.get("country"));
    }
}
