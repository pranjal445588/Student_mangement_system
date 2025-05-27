package dao;

import model.Fee;
import java.sql.*;
import java.util.*;
import util.Database;
public class FeeDao {
    public static void addOrUpdate(Fee f) {
        String sql = "REPLACE INTO fees (admission_number, amount_paid, due) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getAdmissionNumber());
            stmt.setDouble(2, f.getAmountPaid());
            stmt.setDouble(3, f.getDue());
            stmt.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static Fee getByStudent(String admissionNumber) {
        String sql = "SELECT * FROM fees WHERE admission_number=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admissionNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Fee(
                    rs.getInt("id"),
                    rs.getString("admission_number"),
                    rs.getDouble("amount_paid"),
                    rs.getDouble("due")
                );
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
}