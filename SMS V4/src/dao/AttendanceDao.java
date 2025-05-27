package dao;

import model.Attendance;
import java.sql.*;
import java.util.*;
import util.Database;
public class AttendanceDao {
    public static void addOrUpdate(Attendance a) {
        String sql = "REPLACE INTO attendance (admission_number, month, attendance_percentage) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getAdmissionNumber());
            stmt.setString(2, a.getMonth());
            stmt.setFloat(3, a.getAttendancePercentage());
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static List<Attendance> getByStudent(String admissionNumber) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE admission_number=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admissionNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Attendance(
                    rs.getInt("id"),
                    rs.getString("admission_number"),
                    rs.getString("month"),
                    rs.getFloat("attendance_percentage")
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
}