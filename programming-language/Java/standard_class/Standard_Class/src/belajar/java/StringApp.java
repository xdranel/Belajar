package belajar.java;

public class StringApp {
    public static void main(String[] args) {

        String name = "Felix Immanuel";
        String nameLower = name.toLowerCase();
        String nameUpper = name.toUpperCase();

        System.out.println(name);
        System.out.println(nameLower);
        System.out.println(nameUpper);
        System.out.println(name.length());
        System.out.println(name.startsWith("Felix"));
        System.out.println(name.endsWith("Immanuel"));

        String[] nameSplit = name.split(" ");
        for (var value : nameSplit) {
            System.out.println(value);
        }

        System.out.println(" ".isBlank());
        System.out.println(" ".isEmpty());
        System.out.println("".isEmpty());
        System.out.println(name.charAt(1));

        char[] nameChars = name.toCharArray();
        for (var value : nameChars) {
            System.out.println(value);
        }
    }
}
