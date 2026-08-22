package gendhiramona.thread;

public class UserService {

  private final ThreadLocal<String> threadLocal = new ThreadLocal<>();
//    private String user;

    public void setUser(String user) {
        threadLocal.set(user);
//        this.user = user;
    }

    public void doAction() {
        var user = threadLocal.get();
        System.out.println("User: " + user + " is doing something");
    }
}
