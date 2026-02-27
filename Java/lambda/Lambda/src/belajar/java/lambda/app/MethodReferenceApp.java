package belajar.java.lambda.app;

import belajar.java.lambda.util.StringUtil;

import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferenceApp {
    public static void main(String[] args) {

//        Predicate<String> pIsLowerCase = new Predicate<String>() {
//            @Override
//            public boolean test(String value) {
//                return StringUtil.isLowerCase(value);
//            }
//        };

        // Method Reference Static
//        Predicate<String> pIsLowerCase = value -> StringUtil.isLowerCase(value);
        Predicate<String> pIsLowerCase = StringUtil::isLowerCase;
        System.out.println(pIsLowerCase.test("abc"));
        System.out.println(pIsLowerCase.test("ABC"));

        // Method Reference Parameter
//        Function<String, String> functionUpper = new Function<String, String>() {
//            @Override
//            public String apply(String value) {
//                return value.toUpperCase();
//            }
//        };

//        Function<String, String> functionUpper = value -> value.toUpperCase();
        Function<String, String> functionUpper = String::toUpperCase;


        System.out.println(functionUpper.apply("abc"));
    }

    // Method Reference Non-Static
    public void run(){
//        Predicate<String> pIsLowerCase = new Predicate<String>() {
//            @Override
//            public boolean test(String value) {
//                return MethodReferenceApp.this.isLowerCase(value);
//            }
//        };

//        Predicate<String> pIsLowerCase = value -> MethodReferenceApp.this.isLowerCase(value);
        Predicate<String> pIsLowerCase = this::isLowerCase;

        System.out.println(pIsLowerCase.test("abc"));
        System.out.println(pIsLowerCase.test("ABC"));
    }

    public void run2(){
        MethodReferenceApp app = new MethodReferenceApp();
        Predicate<String> pIsLowerCase = app::isLowerCase;

        System.out.println(pIsLowerCase.test("abc"));
        System.out.println(pIsLowerCase.test("ABC"));
    }

    public boolean isLowerCase(String value) {
        for(var chars : value.toCharArray()) {
            if(!Character.isLowerCase(chars)) {
                return false;
            }
        }
        return true;
    }
}
