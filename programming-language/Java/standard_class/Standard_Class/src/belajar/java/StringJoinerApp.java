package belajar.java;

import java.util.StringJoiner;

public class StringJoinerApp {
    public static void main(String[] args) {

        StringJoiner sj = new StringJoiner(",", "[", "]");

        sj.add("Felix");
        sj.add("Immanuel");

        System.out.println(sj.toString());
    }
}
