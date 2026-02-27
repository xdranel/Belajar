package belajar.java.application;

import belajar.java.data.Avanza;
import belajar.java.data.Car;

public class CarApp {
    public static void main(String[] args) {

        Car car = new Avanza();
        System.out.println(car.getTier());
        car.drive();
    }
}
