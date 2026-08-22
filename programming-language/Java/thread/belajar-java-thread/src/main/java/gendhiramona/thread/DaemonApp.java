package gendhiramona.thread;

public class DaemonApp {
    public static void main(String[] args) {
        
        var thread = new Thread(() -> {
            try {
                Thread.sleep(3_000L);
                System.out.println("Hello from a daemon thread");
            } catch (InterruptedException e) {
                return;
            }
        });

        thread.setDaemon(true);
        thread.start();
    }
}
