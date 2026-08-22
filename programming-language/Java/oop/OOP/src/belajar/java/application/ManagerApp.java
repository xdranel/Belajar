package belajar.java.application;

import belajar.java.data.Manager;
import belajar.java.data.VicePresident;

public class ManagerApp {
    public static void main(String[] args) {

        var manager = new Manager("John", "Toyota");
        manager.sayHello("Felix");

        var vp = new VicePresident("Jimmy");
        vp.sayHello("Felix");
    }
}

