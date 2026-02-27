package belajar.java.lambda.app;

import java.util.function.Function;

public class FunctionApp {
    public static void main(String[] args) {

//        Function<String, Integer> functionLength = new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return s.length();
//            }
//        };

        Function<String, Integer> functionLength = value -> value.length();
        System.out.println(functionLength.apply("John"));
    }
}
