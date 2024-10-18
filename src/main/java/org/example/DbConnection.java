package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    static String dbHost = System.getenv("POSTGRES_HOST");
    static String dbPort = System.getenv("POSTGRES_PORT");
    static String dbName = System.getenv("POSTGRES_DB");
    static String dbUser = System.getenv("POSTGRES_USER");
    static String dbPassword = System.getenv("POSTGRES_PASSWORD");

    static String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
    }
}
