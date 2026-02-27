package belajar.java.data;

public class Child extends Parent {
    public String name;
    public void doIt(){
        System.out.println("I am child");
        System.out.println("Parent name is " + super.name);
    }
}