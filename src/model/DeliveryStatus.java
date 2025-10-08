package model;

public class DeliveryStatus {
    private int id;
    private String statusName;

    public DeliveryStatus() {}

    public DeliveryStatus(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }

    @Override
    public String toString() {
        return id + " - " + statusName;
    }
}
