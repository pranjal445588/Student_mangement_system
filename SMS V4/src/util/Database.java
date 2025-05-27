package util;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/sms";
    private static final String USER = "root";
    private static final String PASSWORD = "hello"; // Set your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}