package belajar.java.data;

public class Application {

    public static final int PROCESSORS;

    static {
        System.out.println("Accessing Class Application");
        PROCESSORS = Runtime.getRuntime().availableProcessors();
    }
}
