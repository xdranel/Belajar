package belajar.java.application;

public class StackTraceApp {
    public static void main(String[] args) {

//        try {
//            String[] names = {
//                    "John", "Felix", "Daniel"
//            };
//            System.out.println(names[100]);
//        } catch (Throwable t) {
//            StackTraceElement[] stackTraceElements = t.getStackTrace();
//
//            t.printStackTrace();
//        }

        try {
            sampleError();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void sampleError() {
        try {
            String[] names = {
                    "John", "Felix", "Daniel"
            };
            System.out.println(names[100]);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
