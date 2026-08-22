package belajar.java.application;

import belajar.java.data.Person;

public class PersonApp {
    public static void main(String[] args) {

        var person1 = new Person("John", "123 Main St");
        var person2 = new Person("Daniel");
        var person3 = new Person();

        System.out.println(person1.name);
        System.out.println(person1.address);
        System.out.println(person1.country);

        person1.sayHello("Felix");
    }
}
