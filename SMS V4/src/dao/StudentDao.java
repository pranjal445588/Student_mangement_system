package dao;

import model.Student;
import java.sql.*;
import java.util.*;
import util.Database;
public class StudentDao {
    public static void add(Student s) {
        String sql = "INSERT INTO students (admission_number, name, phone_number, course_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getAdmissionNumber());
            stmt.setString(2, s.getName());
            stmt.setString(3, s.getPhoneNumber());
            stmt.setString(4, s.getCourseName());
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static void update(Student s) {
        String sql = "UPDATE students SET name=?, phone_number=?, course_name=? WHERE admission_number=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getPhoneNumber());
            stmt.setString(3, s.getCourseName());
            stmt.setString(4, s.getAdmissionNumber());
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static void delete(String admissionNumber) {
        String sql = "DELETE FROM students WHERE admission_number=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admissionNumber);
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static Student get(String admissionNumber) {
        String sql = "SELECT * FROM students WHERE admission_number=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admissionNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getString("admission_number"),
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("course_name")
                );
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public static List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getString("admission_number"),
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("course_name")
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
}