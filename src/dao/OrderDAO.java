package dao;

import model.Order;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public void addOrder(Order o) {
        String sql = "INSERT INTO orders (customer_id, shipper_id, status_id, total_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, o.getCustomerId());
            ps.setInt(2, o.getShipperId());
            ps.setInt(3, o.getStatusId());
            ps.setDouble(4, o.getTotalPrice());
            ps.executeUpdate();
            System.out.println("Thêm đơn hàng thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String sql = """
                SELECT o.*, c.name AS customer_name, s.name AS shipper_name, ds.status_name
                FROM orders o
                JOIN customer c ON o.customer_id = c.id
                JOIN shipper s ON o.shipper_id = s.id
                JOIN delivery_status ds ON o.status_id = ds.id
                ORDER BY o.id DESC
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order o = new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("shipper_id"),
                        rs.getInt("status_id"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_price")
                );
                o.setCustomerName(rs.getString("customer_name"));
                o.setShipperName(rs.getString("shipper_name"));
                o.setStatusName(rs.getString("status_name"));
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateStatus(int orderId, int newStatusId) {
        String sql = "UPDATE orders SET status_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newStatusId);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            System.out.println("Cập nhật trạng thái đơn hàng thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Đã xóa đơn hàng!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
