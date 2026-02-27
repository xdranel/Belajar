package belajar.java.lambda.app;

import belajar.java.lambda.SimpleAction;

public class SimpleActionApp {
    public static void main(String[] args) {

//        SimpleAction simpleAction1 = new SimpleAction() {
//            @Override
//            public String action(String name) {
//                return "Hello " + name;
//            }
//        };
//        System.out.println(simpleAction1.action("Joko"));

        SimpleAction simpleAction2 = (String name) -> {
            return "Hello " + name;
        };
        System.out.println(simpleAction2.action("Joko"));

        SimpleAction simpleAction3 = (name) -> {
            return "Hello " + name;
        };
        System.out.println(simpleAction3.action("Joko"));

        SimpleAction simpleAction4 = (String name) -> "Hello " + name;
        SimpleAction simpleAction5 = (name) -> "Hello " + name;
        SimpleAction simpleAction6 = name -> "Hello " + name;
    }
}