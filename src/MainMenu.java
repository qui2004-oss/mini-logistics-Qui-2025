//import dao.DepartmentDAO;
import dao.DepartmentDAO;
import dao.EmployeeDAO;
import model.Department;
import model.Employee;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static final EmployeeDAO empDAO = new EmployeeDAO();
    private static final DepartmentDAO deptDAO = new DepartmentDAO();

    public static void start() {
        int choice;
        do {
            System.out.println("\n===============================");
            System.out.println(" MINI LOGISTICS - QUẢN LÝ NHÂN VIÊN & PHÒNG BAN");
            System.out.println("===============================");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Danh sách nhân viên");
            System.out.println("3. Cập nhật nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Thêm phòng ban");
            System.out.println("6. Danh sách phòng ban");
            System.out.println("7. Cập nhật phòng ban");
            System.out.println("8. Xóa phòng ban");
            System.out.println("9. Tìm kiếm nhân viên theo tên hoặc phòng ban");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            while (!sc.hasNextInt()) {
                System.out.print("Nhập số hợp lệ: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> showEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> addDepartment();
                case 6 -> showDepartments();
                case 7 -> updateDepartment();
                case 8 -> deleteDepartment();
                case 9 -> searchEmployee();
                case 0 -> System.out.println("Tạm biệt!");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void addEmployee() {
        System.out.println("\n--- Thêm nhân viên ---");
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Chức vụ: ");
        String position = sc.nextLine();
        System.out.print("Lương: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        showDepartments();
        System.out.print("Chọn ID phòng ban: ");
        int deptId = sc.nextInt();
        sc.nextLine();

        Employee e = new Employee(name, position, salary, deptId);
        empDAO.addEmployee(e);
    }

    private static void showEmployees() {
        System.out.println("\n--- Danh sách nhân viên ---");
        System.out.println("ID  | Họ tên          | Chức vụ        | Lương      | Phòng ban");
        empDAO.getAllEmployees().forEach(System.out::println);
    }

    private static void updateEmployee() {
        System.out.println("\n--- Cập nhật nhân viên ---");
        System.out.print("Nhập ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Chức vụ mới: ");
        String position = sc.nextLine();
        System.out.print("Lương mới: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        showDepartments();
        System.out.print("ID phòng ban mới: ");
        int deptId = sc.nextInt();
        sc.nextLine();

        Employee e = new Employee(id, name, position, salary, deptId, "");
        empDAO.updateEmployee(e);
    }

    private static void deleteEmployee() {
        System.out.print("Nhập ID cần xóa: ");
        int id = sc.nextInt();
        sc.nextLine();
        empDAO.deleteEmployee(id);
    }

    private static void updateDepartment() {
        System.out.println("\n--- Cập nhật phòng ban ---");
        showDepartments();
        System.out.print("Nhập ID phòng ban muốn sửa: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        deptDAO.updateDepartment(new Department(id, name));
    }

    private static void deleteDepartment() {
        System.out.print("Nhập ID phòng ban muốn xóa: ");
        int id = sc.nextInt();
        sc.nextLine();
        deptDAO.deleteDepartment(id);
    }

    private static void addDepartment() {
        System.out.print("\nNhập tên phòng ban mới: ");
        String name = sc.nextLine();
        deptDAO.addDepartment(new Department(name));
    }

    private static void showDepartments() {
        System.out.println("\n--- Danh sách phòng ban ---");
        deptDAO.getAllDepartments().forEach(System.out::println);
    }

    private static void searchEmployee() {
        System.out.print("\nNhập từ khóa tìm kiếm: ");
        String keyword = sc.nextLine();
        List<Employee> results = empDAO.searchEmployees(keyword);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên nào!");
        } else {
            System.out.println("\n--- Kết quả tìm kiếm ---");
            System.out.println("ID | Họ tên | Chức vụ | Lương | Phòng ban");
            results.forEach(System.out::println);
        }
    }

}
