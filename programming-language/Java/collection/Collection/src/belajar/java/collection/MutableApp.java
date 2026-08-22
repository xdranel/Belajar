package belajar.java.collection;

import belajar.java.collection.data.Person;

import java.util.Arrays;
import java.util.List;

public class MutableApp {
    public static void main(String[] args) {

        Person person = new Person("John", Arrays.asList("Coding", "Gaming"));

        person.addHobby("Coding");
        person.addHobby("Sports");

        doSomethingWithHobbies(person.getHobbies());

        for (var hobby : person.getHobbies()) {
            System.out.println(hobby);
        }
    }

    public static void doSomethingWithHobbies(List<String> hobbies) {
        hobbies.add("Salah");
    }
}
