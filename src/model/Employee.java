package model;

public class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;
    private int departmentId;
    private String departmentName; // để hiển thị JOIN

    // Constructor khi thêm nhân viên mới
    public Employee(String name, String position, double salary, int departmentId) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    // Constructor khi đọc từ DB
    public Employee(int id, String name, String position, double salary, int departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public int getDepartmentId() { return departmentId; }

    @Override
    public String toString() {
        return String.format("%-3d | %-15s | %-15s | %-10.2f | %s",
                id, name, position, salary, departmentName);
    }
}
