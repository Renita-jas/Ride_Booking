package service;

import model.Ride;

public interface PricingStrategy {
    double calculateFare(Ride ride);
}

