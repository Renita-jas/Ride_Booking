package service;

import model.Ride;

public class SurgeFare implements PricingStrategy {
    private double surgeMultiplier;

    public SurgeFare(double surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    public double calculateFare(Ride ride) {
        return ride.getFare() * surgeMultiplier;
    }
}
