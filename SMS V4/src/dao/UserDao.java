package dao;

import java.sql.*;
import util.Database;
public class UserDao {
    public static String authenticate(String username, String password) {
        String sql = "SELECT role FROM users WHERE username=? AND password=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // NOTE: Use hashed passwords in production!
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("role");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exists(String username) {
        String sql = "SELECT 1 FROM users WHERE username=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static void createStudentUser(String username, String password) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'student')";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static void updatePassword(String username, String password) {
        String sql = "UPDATE users SET password=? WHERE username=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, password);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}