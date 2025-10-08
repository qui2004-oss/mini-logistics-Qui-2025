package dao;

import model.Department;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    // CREATE
    public void addDepartment(Department dept) {
        String sql = "INSERT INTO departments (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dept.getName());
            stmt.executeUpdate();
            System.out.println("✅ Thêm phòng ban thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi thêm phòng ban: " + e.getMessage());
        }
    }

    // READ
    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateDepartment(Department dept) {
        String sql = "UPDATE departments SET name=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dept.getName());
            stmt.setInt(2, dept.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Cập nhật thành công!");
            else
                System.out.println("⚠️ Không tìm thấy phòng ban!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteDepartment(int id) {
        String sql = "DELETE FROM departments WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Xóa thành công!");
            else
                System.out.println("⚠️ Không tìm thấy phòng ban!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
