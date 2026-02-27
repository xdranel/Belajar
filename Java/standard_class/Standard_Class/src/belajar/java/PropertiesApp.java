package belajar.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesApp {
    public static void main(String[] args) {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("application.properties"));

            String host = prop.getProperty("database.host");
            String port = prop.getProperty("database.port");
            String username = prop.getProperty("database.username");
            String password = prop.getProperty("database.password");

            System.out.println(host);
            System.out.println(port);
            System.out.println(username);
            System.out.println(password);

        } catch (FileNotFoundException exception) {
            System.out.println("file not found");
        } catch (IOException exception) {
            System.out.println("Can't load file");
        }

        try {
            Properties prop = new Properties();
            prop.put("comment", "do not change anything");
            prop.store(new FileOutputStream("comment.properties"), "comment by me");
        } catch (FileNotFoundException exception) {
            System.out.println("file not found");
        } catch (IOException exception) {
            System.out.println("Can't save file");
        }
    }
}
