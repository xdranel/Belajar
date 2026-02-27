package belajar.java.application;

import belajar.java.annotation.Fancy;
import belajar.java.data.LoginRequest;
import belajar.java.error.ValidationException;
import belajar.java.util.ValidationUtil;

public class ValidationApp {

    @Fancy(name = "ValidationApp", tags = {"application", "java"})
    public static void main(String[] args) {

        LoginRequest loginRequest = new LoginRequest(null, "123lkm");

        try {
            ValidationUtil.validate(loginRequest);
            System.out.println("Data successfully validated");
        } catch (ValidationException | NullPointerException e) {
            System.out.println("Data not Valid " + e.getMessage());
        } finally {
            System.out.println("Always gets executed");
        }

        LoginRequest loginRequest2 = new LoginRequest(null, null);
        ValidationUtil.validateRuntime(loginRequest2);
        System.out.println("Data successfully validated");
    }
}
