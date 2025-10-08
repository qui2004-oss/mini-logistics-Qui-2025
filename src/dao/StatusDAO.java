package dao;

import model.Status;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    public List<Status> getAllStatuses() {
        List<Status> list = new ArrayList<>();
        String sql = "SELECT * FROM delivery_status";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Status(
                        rs.getInt("id"),
                        rs.getString("status_name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
