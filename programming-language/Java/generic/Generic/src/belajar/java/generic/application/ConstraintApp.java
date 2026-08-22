package belajar.java.generic.application;

import belajar.java.generic.MyData;

public class ConstraintApp {
    public static void main(String[] args) {

        NumberData<Integer> integerNumberData = new NumberData<>(100);
        NumberData<Long> longNumberData = new NumberData<>(100000L);
        NumberData<Float> floatNumberData = new NumberData<>(10000F);

        // NumberData<String> stringNumberData = new NumberData<>("Eko"); -- Error
    }

    public static class NumberData<T extends Number>{

        private T data;

        public NumberData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
