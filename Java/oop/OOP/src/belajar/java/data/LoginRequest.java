package belajar.java.data;

public record LoginRequest(String username, String password) {

    public LoginRequest {
        System.out.println("Making Object LoginRequest");
    }

    public LoginRequest(String username) {
        this(username, "");
    }

    public LoginRequest() {
        this("", "");
    }

    public void sayHello() {
        System.out.println("Hello " + username);
    }

    public void setUsername(String username) {
        username = this.username;
    }
}
