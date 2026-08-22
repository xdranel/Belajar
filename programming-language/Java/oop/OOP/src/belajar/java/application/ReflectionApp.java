package belajar.java.application;

import belajar.java.data.CreateUserRequest;
import belajar.java.util.ValidationUtil;

public class ReflectionApp {
    public static void main(String[] args) {

        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("admin");
        request.setPassword("admin");
        request.setRole("admin");

        ValidationUtil.validationReflection(request);
    }
}
