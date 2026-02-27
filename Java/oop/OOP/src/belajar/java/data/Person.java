package belajar.java.data;

public class Person {
    public String name;
    public String address;
    public final String country = "United States";

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Person(String paramName) {
        this(paramName, null);
    }

    public Person() {
        this(null, null);
    }

    public void sayHello(String name) {
        System.out.println("Hello " + name + " My name is " + this.name);
    }
}
