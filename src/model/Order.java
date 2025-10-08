package model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int customerId;
    private int shipperId;
    private int statusId;
    private Timestamp orderDate;
    private double totalPrice;

    private String customerName;
    private String shipperName;
    private String statusName;

    public Order(int id, int customerId, int shipperId, int statusId, Timestamp orderDate, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.shipperId = shipperId;
        this.statusId = statusId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Order(int customerId, int shipperId, int statusId, double totalPrice) {
        this.customerId = customerId;
        this.shipperId = shipperId;
        this.statusId = statusId;
        this.totalPrice = totalPrice;
    }

    // Getter & Setter
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public int getShipperId() { return shipperId; }
    public int getStatusId() { return statusId; }
    public Timestamp getOrderDate() { return orderDate; }
    public double getTotalPrice() { return totalPrice; }

    public void setStatusId(int statusId) { this.statusId = statusId; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setShipperName(String shipperName) { this.shipperName = shipperName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }

    @Override
    public String toString() {
        return String.format(
                "%-3d | %-15s | %-12s | %-10s | %-10.2f | %s",
                id, customerName, shipperName, statusName, totalPrice, orderDate
        );
    }
}
