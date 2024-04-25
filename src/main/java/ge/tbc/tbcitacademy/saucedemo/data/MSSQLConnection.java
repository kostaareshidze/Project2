package ge.tbc.tbcitacademy.saucedemo.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import ge.tbc.tbcitacademy.saucedemo.data.DBConfiguration;


public class MSSQLConnection {
    public static Connection connect() {
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            String jdbcUrl = DBConfiguration.getURL();
            String password = DBConfiguration.getPassword();
            String username = DBConfiguration.getUsername();

            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace
            throw new RuntimeException(e);
        }
    }
}