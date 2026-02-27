package gendhiramona.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    // Gunakan Pada Koneksi biasa
//    static {
//        try {
//            Driver driver = new com.mysql.cj.jdbc.Driver();
//            DriverManager.registerDriver(driver);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @BeforeAll
    static void beforeAll() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String url = "jdbc:mysql://localhost:3306/belajar_java_database";
        String username = "root";
        String password = "plm987!";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testConnectionClos() {
        String url = "jdbc:mysql://localhost:3306/belajar_java_database";
        String username = "root";
        String password = "plm987!";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection established");
//            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


}
