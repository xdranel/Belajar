package belajar.java.generic.application;

import belajar.java.generic.MyData;

public class InvariantApp {
    public static void main(String[] args) {

        // Child To Parent
        MyData<String> stringMyData = new MyData<>("John");
        // doIt(stringMyData); -- Error
        // MyData<Object> objectMyData = stringMyData; -- Error

        // Tidak Ada Hubugannya Polymorphism/Inheritance dengan Generic

        MyData<Object> objectMyData = new MyData<>(1000);
        // doItInteger(objectMyData); -- Error
        // MyData<Integer> integerMyData = objectMyData; -- Error

    }

    public static void doIt(MyData<Object> myData) {
        // Anything
    }

    public static void doItInteger(MyData<Integer> myData) {
        // Anything
    }

}
