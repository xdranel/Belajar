package belajar.java.collection;

import belajar.java.collection.data.Person;
import belajar.java.collection.data.PersonComparator;

import java.util.*;

public class SortedSetApp {
    public static void main(String[] args) {

        SortedSet<Person> people = new TreeSet<>(new PersonComparator());

        people.add(new Person("John", List.of("Coding", "Gaming", "Buying")));
        people.add(new Person("Alice", Arrays.asList("Cycling", "Drawing", "Adventure")));
        people.add(new Person("Bob", List.of("Jogging", "Racing")));

        for (var person : people) {
            System.out.println(person.getName() + " " + person.getHobbies());
        }

        SortedSet<Person> sortedSet = Collections.unmodifiableSortedSet(people);
//        sortedSet.add(new Person("John", List.of("Coding", "Gaming", "Buying")));
    }
}
