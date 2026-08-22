public class MethodOverloading {
    public static void main(String[] args) {

        sayHello();
        sayHello("Budi");
        sayHello("Daniel", 32);
    }

    static void sayHello() {
        System.out.println("Hello");
    }

    static void sayHello(String name) {
        System.out.println("Hello " + name);
    }

    static void sayHello(String name, int age) {
        System.out.println("Hello " + name + " " + age);
    }
}
