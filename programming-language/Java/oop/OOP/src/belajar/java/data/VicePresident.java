package belajar.java.data;

public class VicePresident extends Manager {

    public VicePresident(String name) {
        super(name);
    }

    public void sayHello(String name) {
        System.out.println("Hello " + name + " My name is VP " + this.name);
    }
}
