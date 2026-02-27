package belajar.java.lambda.app;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapForEachApp {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("Name", "John");
        map.put("Age", "25");
        map.put("Country", "India");

        // for loop
        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // forEach Anon
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                System.out.println(key + " : " + value);
            }
        });

        // lambda
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
