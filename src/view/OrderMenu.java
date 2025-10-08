package view;

import dao.OrderDAO;
import dao.CustomerDAO;
import dao.ShipperDAO;
import dao.StatusDAO;
import model.Order;
import java.util.List;
import java.util.Scanner;

public class OrderMenu {
    private final Scanner sc = new Scanner(System.in);
    private final OrderDAO orderDAO = new OrderDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ShipperDAO shipperDAO = new ShipperDAO();
    private final StatusDAO statusDAO = new StatusDAO();

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===============================");
            System.out.println(" QUẢN LÝ ĐƠN HÀNG");
            System.out.println("===============================");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Danh sách đơn hàng");
            System.out.println("3. Cập nhật trạng thái");
            System.out.println("4. Xóa đơn hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addOrder();
                case 2 -> showOrders();
                case 3 -> updateStatus();
                case 4 -> deleteOrder();
                case 0 -> System.out.println("⬅ Quay lại menu chính...");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private void addOrder() {
        System.out.println("\n=== DANH SÁCH KHÁCH HÀNG ===");
        customerDAO.getAllCustomers().forEach(c ->
                System.out.println(c.getId() + " - " + c.getName() + " (" + c.getPhone() + ")")
        );

        System.out.print("\nNhập ID khách hàng: ");
        int customerId = sc.nextInt();

        System.out.println("\n=== DANH SÁCH NGƯỜI GIAO HÀNG ===");
        shipperDAO.getAllShippers().forEach(s ->
                System.out.println(s.getId() + " - " + s.getName())
        );

        System.out.print("\nNhập ID người giao hàng: ");
        int shipperId = sc.nextInt();

        System.out.println("\n=== TRẠNG THÁI GIAO HÀNG ===");
        statusDAO.getAllStatuses().forEach(s ->
                System.out.println(s.getId() + ". " + s.getStatusName())
        );

        System.out.print("\nNhập ID trạng thái: ");
        int statusId = sc.nextInt();

        System.out.print("Nhập tổng tiền: ");
        double totalPrice = sc.nextDouble();

        Order o = new Order(customerId, shipperId, statusId, totalPrice);
        orderDAO.addOrder(o);
    }

    private void showOrders() {
        List<Order> list = orderDAO.getAllOrders();
        if (list.isEmpty()) {
            System.out.println(" Chưa có đơn hàng nào.");
            return;
        }

        System.out.println("\nID | Khách hàng     | Shipper      | Trạng thái | Tổng tiền | Ngày đặt");
        list.forEach(System.out::println);
    }

    private void updateStatus() {
        System.out.print("Nhập ID đơn hàng cần cập nhật: ");
        int id = sc.nextInt();

        System.out.println("\n=== TRẠNG THÁI HIỆN CÓ ===");
        statusDAO.getAllStatuses().forEach(s ->
                System.out.println(s.getId() + ". " + s.getStatusName())
        );

        System.out.print("Nhập ID trạng thái mới: ");
        int newStatus = sc.nextInt();

        orderDAO.updateStatus(id, newStatus);
    }

    private void deleteOrder() {
        System.out.print("Nhập ID đơn hàng cần xóa: ");
        int id = sc.nextInt();
        orderDAO.deleteOrder(id);
    }
}
