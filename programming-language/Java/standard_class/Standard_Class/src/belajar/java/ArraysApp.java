package belajar.java;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArraysApp {
    public static void main(String[] args) {

        int[] numbers = {
                1, 3, 546, 78, 29, 23, 123, 9, 0, 24, 46, 8, 768
        };

        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));

        System.out.println(Arrays.binarySearch(numbers, 24));
        System.out.println(Arrays.binarySearch(numbers, 100));

        int[] results = Arrays.copyOf(numbers, 5);
        System.out.println(Arrays.toString(results));

        int[] resultsRange = Arrays.copyOfRange(numbers, 3, 7);
        System.out.println(Arrays.toString(resultsRange));

        boolean equals = Arrays.equals(resultsRange, results);
        if (equals) {
            System.out.println("Equals");
        } else  {
            System.out.println("Not Equals");
        }
    }
}
