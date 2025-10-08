//package model;
//
//public class Customer {
//    private int id;
//    private String name;
//    private String phone;
//    private String address;
//
//    public Customer() {}
//
//    public Customer(int id, String name, String phone, String address) {
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//        this.address = address;
//    }
//
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getPhone() { return phone; }
//    public void setPhone(String phone) { this.phone = phone; }
//
//    public String getAddress() { return address; }
//    public void setAddress(String address) { this.address = address; }
//
//    @Override
//    public String toString() {
//        return String.format("%d - %s (%s) - %s", id, name, phone, address);
//    }
//}
package model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String address;

    public Customer() {}

    public Customer(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // ➕ Constructor dùng khi thêm khách hàng mới (chưa có ID)
    public Customer(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("%d - %s (%s) - %s", id, name, phone, address);
    }
}
