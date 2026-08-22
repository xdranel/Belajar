package util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static String input(String prompt) {
        System.out.print(prompt + " : ");
        String data = scanner.nextLine();
        return data;
    }
}
