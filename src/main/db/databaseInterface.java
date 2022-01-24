package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface databaseInterface {
    String URL = "jdbc:postgresql://localhost:5432/swe1user";
    String username = "swe1user";
    String password = "swe1pw";

    static Connection getConnection() {
        //build a connection with the database
        try {
            return DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
