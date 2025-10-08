package dao;

import model.Employee;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // CREATE
    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, position, salary, department_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getPosition());
            stmt.setDouble(3, emp.getSalary());
            stmt.setInt(4, emp.getDepartmentId());
            stmt.executeUpdate();
            System.out.println("✅ Thêm nhân viên thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (JOIN)
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = """
                SELECT e.id, e.name, e.position, e.salary, d.name AS department_name
                FROM employees e
                LEFT JOIN departments d ON e.department_id = d.id
                ORDER BY e.id ASC
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        0,
                        rs.getString("department_name")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name=?, position=?, salary=?, department_id=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getPosition());
            stmt.setDouble(3, emp.getSalary());
            stmt.setInt(4, emp.getDepartmentId());
            stmt.setInt(5, emp.getId());
            stmt.executeUpdate();
            System.out.println("✅ Cập nhật nhân viên thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Xóa nhân viên thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SEARCH
    public List<Employee> searchEmployees(String keyword) {
        List<Employee> list = new ArrayList<>();
        String sql = """
                SELECT e.id, e.name, e.position, e.salary, d.name AS department_name
                FROM employees e
                LEFT JOIN departments d ON e.department_id = d.id
                WHERE e.name LIKE ? OR d.name LIKE ?
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        0,
                        rs.getString("department_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
