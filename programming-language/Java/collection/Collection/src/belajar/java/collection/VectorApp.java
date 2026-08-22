package belajar.java.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorApp {
    public static void main(String[] args) {

        List<String> list = new Vector<>();

        list.add("1");
        list.add("2");
        list.add("3");

        for (var value : list) {
            System.out.println(value);
        }
    }
}
