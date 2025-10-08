import view.OrderMenu;
import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.CustomerDAO;
import model.Department;
import model.Employee;
import model.Customer;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static final EmployeeDAO empDAO = new EmployeeDAO();
    private static final DepartmentDAO deptDAO = new DepartmentDAO();
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final OrderMenu orderMenu = new OrderMenu();

    public static void start() {
        int choice;
        do {
            System.out.println("\n===============================");
            System.out.println("   HỆ THỐNG MINI LOGISTICS");
            System.out.println("===============================");
            System.out.println("1. Quản lý phòng ban");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý khách hàng");
            System.out.println("4. Quản lý đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            while (!sc.hasNextInt()) {
                System.out.print("Nhập số hợp lệ: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> departmentMenu();
                case 2 -> employeeMenu();
                case 3 -> customerMenu();
                case 4 -> orderMenu.showMenu();
                case 0 -> System.out.println("Tạm biệt");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // ====== PHÒNG BAN ======
    private static void departmentMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Phòng ban ---");
            System.out.println("1. Thêm phòng ban");
            System.out.println("2. Danh sách phòng ban");
            System.out.println("3. Cập nhật phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Tên phòng ban: ");
                    String name = sc.nextLine();
                    deptDAO.addDepartment(new Department(name));
                }
                case 2 -> deptDAO.getAllDepartments().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Nhập ID phòng ban: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tên mới: ");
                    String newName = sc.nextLine();
                    deptDAO.updateDepartment(new Department(id, newName));
                }
                case 4 -> {
                    System.out.print("Nhập ID phòng ban cần xóa: ");
                    int id = sc.nextInt();
                    deptDAO.deleteDepartment(id);
                }
                case 0 -> {}
                default -> System.out.println("Sai lựa chọn!");
            }
        } while (choice != 0);
    }

    // ====== NHÂN VIÊN ======
    private static void employeeMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Nhân viên ---");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Danh sách nhân viên");
            System.out.println("3. Cập nhật nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("Chức vụ: ");
                    String position = sc.nextLine();
                    System.out.print("Lương: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("ID phòng ban: ");
                    int deptId = sc.nextInt();
                    empDAO.addEmployee(new Employee(name, position, salary, deptId));
                }
                case 2 -> empDAO.getAllEmployees().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Nhập ID nhân viên: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tên mới: ");
                    String name = sc.nextLine();
                    System.out.print("Chức vụ mới: ");
                    String pos = sc.nextLine();
                    System.out.print("Lương mới: ");
                    double sal = sc.nextDouble();
                    System.out.print("ID phòng ban mới: ");
                    int dept = sc.nextInt();
                    empDAO.updateEmployee(new Employee(id, name, pos, sal, dept, ""));
                }
                case 4 -> {
                    System.out.print("Nhập ID cần xóa: ");
                    int id = sc.nextInt();
                    empDAO.deleteEmployee(id);
                }
                case 0 -> {}
                default -> System.out.println("Sai lựa chọn!");
            }
        } while (choice != 0);
    }

    // ====== KHÁCH HÀNG ======
    private static void customerMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Khách hàng ---");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Danh sách khách hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Tên khách hàng: ");
                    String name = sc.nextLine();
                    System.out.print("SĐT: ");
                    String phone = sc.nextLine();
                    System.out.print("Địa chỉ: ");
                    String address = sc.nextLine();
                    customerDAO.addCustomer(new Customer(name, phone, address));
                }
                case 2 -> customerDAO.getAllCustomers().forEach(System.out::println);
                case 0 -> {}
                default -> System.out.println("Sai lựa chọn!");
            }
        } while (choice != 0);
    }
}
