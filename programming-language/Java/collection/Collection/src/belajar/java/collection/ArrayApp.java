package belajar.java.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayApp {
    public static void main(String[] args) {

        List<String> list = List.of("A","B","C","D","E","F","G");

        Object[] objects = list.toArray();
        String[] strings = list.toArray(new String[]{});

        System.out.println(Arrays.toString(objects));
        System.out.println(Arrays.toString(strings));
    }
}
