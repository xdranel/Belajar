package belajar.java.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSetApp {
    public static void main(String[] args) {

        Set<String> empty = Collections.emptySet();
        Set<String> one = Collections.singleton("one");

        Set<String> mutable = new HashSet<>();
        mutable.add("one");
        mutable.add("two");
        Set<String> immutable = Collections.unmodifiableSet(mutable);

        Set<String> set = Set.of("one", "two");
        set.add("one");
    }
}
