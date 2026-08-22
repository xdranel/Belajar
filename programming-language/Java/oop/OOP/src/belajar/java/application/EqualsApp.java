package belajar.java.application;

public class EqualsApp {
    public static void main(String[] args) {

        String first = "Joko";
        first = first + " " + "Hermawan";

        System.out.println(first);

        String second = "Joko Hermawan";
        System.out.println(second);

        System.out.println(first == second);
        System.out.println(first.equals(second));

        String third = "Joko Hermawan";
        System.out.println(second == third);
        System.out.println(second.equals(third));
    }
}
