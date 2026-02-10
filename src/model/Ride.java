package model;

public class Ride {
    private String rideId;
    private User user;
    private Driver driver;
    private String startLocation;
    private String endLocation;
    private double fare;
    private String status; // "BOOKED", "IN_PROGRESS", "COMPLETED"

    public Ride(String rideId, User user, Driver driver, String startLocation, String endLocation, double fare) {
        this.rideId = rideId;
        this.user = user;
        this.driver = driver;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.fare = fare;
        this.status = "BOOKED";
    }

    public String getRideId() { return rideId; }
    public User getUser() { return user; }
    public Driver getDriver() { return driver; }
    public String getStartLocation() { return startLocation; }
    public String getEndLocation() { return endLocation; }
    public double getFare() { return fare; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Ride " + rideId + ": " + user.getName() + " with " + driver.getName() + " from " +
               startLocation + " to " + endLocation + " | Fare: " + fare + " | Status: " + status;
    }
}
