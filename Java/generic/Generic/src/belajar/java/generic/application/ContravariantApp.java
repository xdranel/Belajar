package belajar.java.generic.application;

import belajar.java.generic.MyData;

public class ContravariantApp {
    public static void main(String[] args) {

        MyData<Object> objectMyData = new MyData<>("John");
        // Merubah dari string ke integer
        objectMyData.setData(1000);

        MyData<? super String> stringMyData = objectMyData;
        process(stringMyData);

        System.out.println("Data: " + stringMyData.getData());
    }

    public static void process(MyData<? super String> myData) {
        // String value = myData.getData(); -- Error
        // Jika ingin melakukan getData harus di paksa TypeCast

        String value = (String) myData.getData();
        System.out.println("Processing data: " + value);
        // Tetapi ini pun berbahaya jikalau Object dirubah menjadi type lainnya -- Line 9

        // Jika mau menggunakan Object
        Object values = myData.getData();
        System.out.println("Processing data: " + values);

        myData.setData("Eko");
    }
}
