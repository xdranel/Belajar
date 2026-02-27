package gendhiramona.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {

    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin WHERE username = '" + username +
                "' AND password = '" + password + "'";
        System.out.println(sql);

        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            System.out.println("Success Login: " + resultSet.getString("username"));
        } else {
            System.out.println("Failed Login");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
