package belajar.java;

import java.util.StringTokenizer;

public class StringTokenizerApp {
    public static void main(String[] args) {

        String name = "Felix Immanuel";

        StringTokenizer st = new StringTokenizer(name, " ");

        while (st.hasMoreTokens()) {
            String result = st.nextToken();
            System.out.println(result);
        }
    }
}
