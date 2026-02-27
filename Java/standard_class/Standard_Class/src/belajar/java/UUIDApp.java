package belajar.java;

import java.util.UUID;

public class UUIDApp {
    public static void main(String[] args) {

        for (var i = 0; i < 10; i++) {
            UUID uuid = UUID.randomUUID();

            String key = uuid.toString().replaceAll("-", "");
            System.out.println(key);
        }
    }
}
