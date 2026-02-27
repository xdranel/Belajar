package belajar.java.collection;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class EnumHashMapApp {

    public static enum Level {
        FREE, STANDARD, PREMIUM, VIP
    }

    public static void main(String[] args) {

        Map<Level, String> map = new EnumMap<Level, String>(Level.class);

        map.put(Level.FREE, "John");
        map.put(Level.STANDARD, "Felix");
        map.put(Level.PREMIUM, "Jane");
        map.put(Level.VIP, "Peter");

        Set<Level> set = map.keySet();
        for (var key : set) {
            System.out.println(map.get(key));
        }
    }
}
