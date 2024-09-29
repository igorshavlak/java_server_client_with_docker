package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    static String url = "jdbc:postgresql://localhost:5432/apiuser";
    static String username = "postgres";
    static String password = "postgresql";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
