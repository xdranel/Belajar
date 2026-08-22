package belajar.java.data;

public class Manager extends Employee {
    String company;

    public Manager(String name) {
        super(name);
    }

    public Manager(String name, String company) {
        super(name);
        this.company = company;
    }

    public void sayHello(String name) {
        System.out.println("Hello " + name + " My name is Manager " + this.name);
    }
}
