package service;

import model.Ride;

public class StandardFare implements PricingStrategy {
    public double calculateFare(Ride ride) {
        return ride.getFare(); // base fare already calculated using Dijkstra
    }
}
