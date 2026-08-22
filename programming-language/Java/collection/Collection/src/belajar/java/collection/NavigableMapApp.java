package belajar.java.collection;

import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapApp {
    public static void main(String[] args) {

        NavigableMap<String, String> map = new TreeMap<>();

        map.put("John", "Doe");
        map.put("Jane", "Doe");
        map.put("Julie", "Doe");

        for (var key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

//        System.out.println(map.lowerEntry("John"));
//        System.out.println(map.lowerKey("John"));

        NavigableMap<String, String> mapDesc = map.descendingMap();

        for (var key : mapDesc.keySet()) {
            System.out.println(key + " : " + mapDesc.get(key));
        }

        NavigableMap<String, String> empty = Collections.emptyNavigableMap();
        NavigableMap<String, String> immutable = Collections.unmodifiableNavigableMap(map);

        // immutable.put("John", "Doe"); -- Error

    }
}
