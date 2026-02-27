package belajar.java.application;

import belajar.java.data.City;
import belajar.java.data.Location;

public class LocationApp {
    public static void main(String[] args) {

//        var location = new Location(); Error
        var city = new City();
        city.name = "Berlin";
        System.out.println(city.name);
    }
}
