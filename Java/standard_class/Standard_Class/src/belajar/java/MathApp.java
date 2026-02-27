package belajar.java;

public class MathApp {
    public static void main(String[] args) {

        var min = Math.min(100, 200);
        System.out.println(min);
        var max = Math.max(100, 500);
        System.out.println(max);

//        int abs = Math.abs(min - max);
//        System.out.println(abs);

        System.out.println(Math.abs(min - max));
        System.out.println(Math.abs(min + max));
        System.out.println(Math.PI);
    }
}
