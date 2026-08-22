package belajar.java.application;

import belajar.java.data.LoginRequest;

public class RecordApp {
    public static void main(String[] args) {

        LoginRequest loginRequest = new LoginRequest("admin", "admin");

        System.out.println(loginRequest.username());
        System.out.println(loginRequest.password());
        System.out.println(loginRequest);

        System.out.println(new LoginRequest());
        System.out.println(new LoginRequest("user1"));
        System.out.println(new LoginRequest("user2", "admin"));
    }
}
