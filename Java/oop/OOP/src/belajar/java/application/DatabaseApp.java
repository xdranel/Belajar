package belajar.java.application;

import belajar.java.error.DatabaseError;

public class DatabaseApp {
    public static void main(String[] args) {
        connectDatabase(null,  "password");
        System.out.println("Success");
    }

    public static void connectDatabase(String username, String password) {
        if (username == null || password == null) {
            throw new DatabaseError("Can't connect into database");
        }
    }
}
