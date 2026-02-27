package belajar.java.generic.application;

import belajar.java.generic.util.ArrayHelper;

public class ArrayHelperApp {
    public static void main(String[] args) {

        String[] username = {"Felix", "Robert", "John"};
        String[] password = {"rahasia9", "rahasia2", "rahasia3"};

        System.out.println(
                ArrayHelper.data(username, password)
        );
    }
}
