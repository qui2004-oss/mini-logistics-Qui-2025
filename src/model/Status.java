package model;

public class Status {
    private int id;
    private String statusName;

    public Status(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public int getId() { return id; }
    public String getStatusName() { return statusName; }
}
