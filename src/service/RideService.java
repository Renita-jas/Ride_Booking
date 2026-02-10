package service;

import model.User;
import model.Driver;
import model.Ride;
import java.util.*;
import service.PricingStrategy;
import service.StandardFare;
import service.SurgeFare;
import service.DiscountFare;

public class RideService {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Driver> drivers = new HashMap<>();
    private Map<String, Ride> activeRides = new HashMap<>();
    private Map<String, List<Ride>> rideHistory = new HashMap<>();
    private int rideCounter = 1;
    private LocationService locationService = new LocationService();
    private PricingStrategy pricingStrategy = new StandardFare();

    public RideService() {
        locationService.addEdge("A", "B", 5);
        locationService.addEdge("B", "C", 7);
        locationService.addEdge("A", "C", 10);
        locationService.addEdge("C", "D", 3);
        locationService.addEdge("B", "D", 8);
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addUser(String userId, String name, String location) {
        User user = new User(userId, name, location);
        users.put(userId, user);
        System.out.println("User added: " + user);
    }

    public void addDriver(String driverId, String name, String location) {
        Driver driver = new Driver(driverId, name, location);
        drivers.put(driverId, driver);
        System.out.println("Driver added: " + driver);
    }

    public void bookRide(String userId, String start, String end) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        PriorityQueue<Driver> pq = new PriorityQueue<>(Comparator.comparingInt(d -> locationService.getShortestDistance(d.getLocation(), start)));
        for (Driver d : drivers.values()) {
            if (d.isAvailable()) pq.add(d);
        }

        if (pq.isEmpty()) {
            System.out.println("No available drivers at the moment.");
            return;
        }

        Driver nearestDriver = pq.poll();
        double baseFare = calculateFare(start, end);
        Ride ride = new Ride("R" + rideCounter++, user, nearestDriver, start, end, baseFare);
        double finalFare = pricingStrategy.calculateFare(ride);
        ride = new Ride(ride.getRideId(), user, nearestDriver, start, end, finalFare);

        nearestDriver.setAvailable(false);
        activeRides.put(ride.getRideId(), ride);

        System.out.println("Ride booked successfully!");
        System.out.println(ride);
    }

    public void completeRide(String rideId) {
        Ride ride = activeRides.get(rideId);
        if (ride == null) {
            System.out.println("Ride not found!");
            return;
        }
        ride.setStatus("COMPLETED");
        ride.getDriver().setAvailable(true);
        activeRides.remove(rideId);

        rideHistory.computeIfAbsent(ride.getUser().getUserId(), k -> new ArrayList<>()).add(ride);
        System.out.println("Ride completed successfully!");
        System.out.println(ride);
    }

    public void showActiveRides() {
        if (activeRides.isEmpty()) {
            System.out.println("No active rides.");
            return;
        }
        for (Ride ride : activeRides.values()) System.out.println(ride);
    }

    public void showRideHistory(String userId) {
        List<Ride> rides = rideHistory.get(userId);
        if (rides == null || rides.isEmpty()) {
            System.out.println("No ride history for user " + userId);
            return;
        }
        for (Ride r : rides) System.out.println(r);
    }

    private double calculateFare(String start, String end) {
        int shortestDistance = locationService.getShortestDistance(start, end);
        if (shortestDistance == Integer.MAX_VALUE) return 0;
        return shortestDistance * 10 + 50;
    }
}
