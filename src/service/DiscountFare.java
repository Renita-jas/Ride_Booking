package service;

import model.Ride;

public class DiscountFare implements PricingStrategy {
    private double discount; // 0.1 for 10%

    public DiscountFare(double discount) {
        this.discount = discount;
    }

    public double calculateFare(Ride ride) {
        return ride.getFare() * (1 - discount);
    }
}
