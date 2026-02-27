package belajar.java.collection;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetApp {

    public static enum Gender {
        MALE, FEMALE, NEUTRAL;
    }

    public static void main(String[] args) {

//        EnumSet<Gender> genders = EnumSet.of(Gender.MALE, Gender.FEMALE);
        EnumSet<Gender> genders = EnumSet.allOf(Gender.class);
//        Set<Gender> genders = EnumSet.of(Gender.MALE, Gender.FEMALE);

        for (Gender gender : genders) {
            System.out.println(gender);
        }

//        Gender[] values = Gender.values();
//        for (Gender gender : values) {
//            System.out.println(gender);
//        }
    }
}
