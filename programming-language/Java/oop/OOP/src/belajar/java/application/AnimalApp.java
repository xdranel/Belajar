package belajar.java.application;

import belajar.java.annotation.Fancy;
import belajar.java.data.Animal;
import belajar.java.data.Cat;

@Fancy(name = "AnimalApp", tags = {"application", "java"})
public class AnimalApp {
    public static void main(String[] args) {

        Animal animal = new Cat();
        animal.name = "wiwiwi";
        animal.run();
    }
}
