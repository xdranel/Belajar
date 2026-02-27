package belajar.java.application;

import belajar.java.data.Child;
import belajar.java.data.Parent;

public class ParentApp {
    public static void main(String[] args) {

        Child child = new Child();
        child.name = "Jimmy";
        child.doIt();
        System.out.println(child.name);

        Parent parent = (Parent) child;
        parent.doIt();
        System.out.println(parent.name);

    }
}

