package belajar.java.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImmutableMapApp {
    public static void main(String[] args) {

        Map<String, String> empty = Collections.emptyMap();
        Map<String, String> singleton = Collections.singletonMap("Name", "John");

        Map<String, String> mutable = new HashMap<>();
        mutable.put("Name", "John");
        mutable.put("Surname", "Doe");
        Map<String, String> immutable = Collections.unmodifiableMap(mutable);

        Map<String, String> map = Map.of(
                "Name", "John",
                "Age", "25",
                "Country", "NA"
        );
        // map.putAll(immutable); -- Error
    }
}
