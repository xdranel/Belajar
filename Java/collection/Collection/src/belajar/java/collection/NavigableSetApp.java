package belajar.java.collection;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class NavigableSetApp {
    public static void main(String[] args) {

        NavigableSet<String> people = new TreeSet<>();
        people.addAll(Set.of("John", "Immanuel", "Daniel", "Sam", "Bob", "Logan", "Clarissa"));

        NavigableSet<String> peopleReverse = people.descendingSet();
//        NavigableSet<String> specificPeople = people.headSet("John", true);
        NavigableSet<String> specificPeople = people.tailSet("John", false);

        for(String name : specificPeople) {
            System.out.println(name);
        }

        NavigableSet<String> immutable = Collections.unmodifiableNavigableSet(people);
//        immutable.add("Benjamin");
    }
}
