package belajar.java.generic.application;

import belajar.java.generic.Person;

import java.util.Arrays;

public class ComparableApp {
    public static void main(String[] args) {

        Person[] people = {
                new Person("John Doe", "North America"),
                new Person("Ricardo Dohan", "South America"),
                new Person("Felix Nando", "Denmark"),
        };

        Arrays.sort(people);

        System.out.println(Arrays.toString(people));
    }
}
