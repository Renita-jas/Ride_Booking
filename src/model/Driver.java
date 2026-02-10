package model;

public class Driver {
    private String driverId;
    private String name;
    private String location;
    private boolean available;

    public Driver(String driverId, String name, String location) {
        this.driverId = driverId;
        this.name = name;
        this.location = location;
        this.available = true;
    }

    public String getDriverId() { return driverId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return name + " (" + driverId + ") at " + location + (available ? " [Available]" : " [Busy]");
    }
}
