package belajar.java.application;

import static belajar.java.data.Application.*;
import static belajar.java.data.Constant.*;
import static belajar.java.data.Country.*;
import static belajar.java.util.MathUtil.*;

public class StaticApp {
    public static void main(String[] args) {

        System.out.println(APPLICATION);
        System.out.println(VERSION);

        System.out.println(
                sum(10, 2, 5, 9, 8, 6)
        );

        City city = new City();
        city.setName("Madrid");
        System.out.println(city.getName());

        System.out.println(PROCESSORS);
    }
}
