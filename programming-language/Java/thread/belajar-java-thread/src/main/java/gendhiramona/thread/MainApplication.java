package gendhiramona.thread;

public class MainApplication {
    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println("Main thread name: " + name);
    }
}
