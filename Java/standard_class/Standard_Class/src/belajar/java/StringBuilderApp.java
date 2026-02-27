package belajar.java;

public class StringBuilderApp {
    public static void main(String[] args) {

        StringBuilder builder = new StringBuilder();
        builder.append("Felix");
        builder.append(" Immanuel");
//        System.out.println(builder.toString());

        String name = builder.toString();
        System.out.println(name);
    }
}
