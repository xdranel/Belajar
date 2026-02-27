package belajar.java.application;

import belajar.java.data.Employee;
import belajar.java.data.Manager;
import belajar.java.data.VicePresident;

public class PolymorphismApp {
    public static void main(String[] args) {

        Employee employee = new Employee("John");
        employee.sayHello("Felix");

        employee = new Manager("John");
        employee.sayHello("Felix");

        employee = new VicePresident("John");
        employee.sayHello("Felix");

        sayHello(new Employee("John"));
        sayHello(new Manager("Daniel"));
        sayHello(new VicePresident("Felix"));
    }

    static void sayHello(Employee employee) {
        if (employee instanceof VicePresident) {
            VicePresident vicePresident = (VicePresident) employee;
            System.out.println("Hello Vp " + vicePresident.name);
        } else if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            System.out.println("Hello Manager " + manager.name);
        } else  {
            System.out.println("Hello " + employee.name);
        }

    }
}

