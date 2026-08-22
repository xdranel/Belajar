package belajar.java.lambda.app;

import java.util.function.Supplier;

public class SupplierApp {
    public static void main(String[] args) {

//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "Hello World";
//            }
//        };
//        System.out.println(supplier.get());

        Supplier<String> supplier = () -> "Hello World";
        System.out.println(supplier.get());
    }
}
