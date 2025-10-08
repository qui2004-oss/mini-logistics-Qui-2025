# 🚛 Mini Logistics Management System

Ứng dụng quản lý nhân viên và phòng ban viết bằng **Java + MySQL (JDBC)**.

---

## ⚙️ Công nghệ sử dụng
- Java 22 (Corretto)
- MySQL 8.x
- JDBC
- IntelliJ IDEA

---

## 📦 Tính năng
- Quản lý **phòng ban** (Thêm, xem, sửa, xóa)
- Quản lý **nhân viên** (Thêm, xem, sửa, xóa)
- Hiển thị thông tin nhân viên kèm tên phòng ban (JOIN)
- Tìm kiếm hoặc cập nhật dữ liệu qua menu console

---

## 🧩 Cấu trúc dự án
```
src/
 ├─ model/           # Chứa các lớp Employee, Department
 ├─ dao/             # Lớp truy cập dữ liệu (DAO)
 ├─ util/            # Lớp DatabaseConnection (kết nối MySQL)
 ├─ MainMenu.java    # Menu console chính
 └─ Main.java        # Điểm khởi động ứng dụng
```

---

## 🛠️ Cài đặt & Chạy dự án

### 1️⃣ Tạo database MySQL
```sql
CREATE DATABASE mini_logistics;
USE mini_logistics;

CREATE TABLE departments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  position VARCHAR(100),
  salary DOUBLE,
  department_id INT,
  FOREIGN KEY (department_id) REFERENCES departments(id)
);
```

---

### 2️⃣ Cấu hình kết nối (file `DatabaseConnection.java`)
```java
private static final String URL = "jdbc:mysql://localhost:3306/mini_logistics";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```
> 💡 Thay `your_password` bằng mật khẩu MySQL thực tế của bạn (ví dụ `root123`).

---

### 3️⃣ Chạy chương trình
- Mở file `Main.java`
- Nhấn **Run ▶**
- Menu CRUD sẽ hiển thị trong console 🎉

---

## 👨‍💻 Tác giả
**Nguyễn Anh Qui – 2025**  
📎 *GitHub Repository:* [https://github.com/yourusername/mini-logistics](https://github.com/yourusername/mini-logistics)
