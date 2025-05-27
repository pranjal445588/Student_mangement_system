package dao;

import model.Notice;
import java.sql.*;
import java.util.*;
import util.Database;
public class NoticeDao {
    public static void add(Notice n) {
        String sql = "INSERT INTO notices (course_name, message) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, n.getCourseName());
            stmt.setString(2, n.getMessage());
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static List<Notice> getByCourse(String courseName) {
        List<Notice> list = new ArrayList<>();
        String sql = "SELECT * FROM notices WHERE course_name=? ORDER BY date_posted DESC";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Notice(
                    rs.getInt("id"),
                    rs.getString("course_name"),
                    rs.getString("message"),
                    rs.getTimestamp("date_posted")
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
}