package belajar.java.collection;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class HashTableApp {
    public static void main(String[] args) {

        Map<String, String> map = new Hashtable<>();

        map.put("Height", "6'5");
        map.put("Age", "25");

        Set<String> keySet = map.keySet();
        for (var key : keySet) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
