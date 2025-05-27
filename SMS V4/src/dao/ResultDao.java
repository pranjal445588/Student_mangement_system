package dao;

import model.Result;
import java.sql.*;
import java.util.*;
import util.Database;
public class ResultDao {
    public static void add(Result r) {
        String sql = "INSERT INTO results (admission_number, exam_type, subject, marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getAdmissionNumber());
            stmt.setString(2, r.getExamType());
            stmt.setString(3, r.getSubject());
            stmt.setInt(4, r.getMarks());
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static List<Result> getByStudent(String admissionNumber) {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM results WHERE admission_number=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admissionNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Result(
                    rs.getInt("id"),
                    rs.getString("admission_number"),
                    rs.getString("exam_type"),
                    rs.getString("subject"),
                    rs.getInt("marks")
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    public static void update(Result r) {
        String sql = "UPDATE results SET exam_type=?, subject=?, marks=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getExamType());
            stmt.setString(2, r.getSubject());
            stmt.setInt(3, r.getMarks());
            stmt.setInt(4, r.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}