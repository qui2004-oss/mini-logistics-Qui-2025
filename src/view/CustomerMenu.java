package view;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private final CustomerDAO dao = new CustomerDAO();

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== QUẢN LÝ KHÁCH HÀNG ===");
            System.out.println("1. Xem tất cả khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    List<Customer> customers = dao.getAllCustomers();
                    customers.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("SĐT: ");
                    String phone = sc.nextLine();
                    System.out.print("Địa chỉ: ");
                    String address = sc.nextLine();

                    dao.addCustomer(new Customer(0, name, phone, address));
                    System.out.println("✅ Đã thêm khách hàng mới!");
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
            }
        } while (choice != 0);
    }
}
