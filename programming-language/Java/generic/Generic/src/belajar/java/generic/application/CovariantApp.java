package belajar.java.generic.application;

import belajar.java.generic.MyData;

public class CovariantApp {
    public static void main(String[] args) {

        MyData<String> stringMyData = new MyData<>("John");
        process(stringMyData);
        MyData<Integer> integerMyData = new MyData<>(100);
        process(integerMyData);

        MyData<? extends Object> objectMyData = stringMyData;
    }

    // Hanya Boleh Mengambil Data tidak Boleh Merubah

    public static void process(MyData<? extends Object> myData) {
        System.out.println(myData.getData());
        // myData.setData(); -- Error
    }
}
