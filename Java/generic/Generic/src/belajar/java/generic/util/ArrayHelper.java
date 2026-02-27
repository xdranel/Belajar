package belajar.java.generic.util;

public class ArrayHelper {

    public static <T, U> int data(T[] array1, U[] array2) {
//        for (int i = 0; i < array1.length; i++) {
//            System.out.println(array1[i] + " Berumur " + array2[i]);
//        }

        String[] username =  new String[array1.length];
        String[] password = new String[array2.length];
        for (int i = 0; i < array1.length; i++) {
            username[i] = array1[i].toString();
            password[i] = array2[i].toString();
        }

        for (int i = 0; i < username.length; i++) {
            System.out.println(username[i] + " " + password[i].hashCode());
        }
        return 0;
    }
}
