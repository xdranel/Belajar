package belajar.java.data;

import belajar.java.annotation.NotBlank;

public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String role;

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
