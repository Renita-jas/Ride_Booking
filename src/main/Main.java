package main;

import service.RideService;
import service.StandardFare;
import service.SurgeFare;
import service.DiscountFare;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RideService rideService = new RideService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- RIDE BOOKING SYSTEM ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Driver");
            System.out.println("3. Book Ride");
            System.out.println("4. Complete Ride");
            System.out.println("5. Show Active Rides");
            System.out.println("6. Show Ride History");
            System.out.println("7. Set Pricing Strategy");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("User ID: ");
                    String uid = sc.nextLine();
                    System.out.print("Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Location: ");
                    String uloc = sc.nextLine();
                    rideService.addUser(uid, uname, uloc);
                }
                case 2 -> {
                    System.out.print("Driver ID: ");
                    String did = sc.nextLine();
                    System.out.print("Name: ");
                    String dname = sc.nextLine();
                    System.out.print("Location: ");
                    String dloc = sc.nextLine();
                    rideService.addDriver(did, dname, dloc);
                }
                case 3 -> {
                    System.out.print("User ID: ");
                    String uid = sc.nextLine();
                    System.out.print("Start Location: ");
                    String start = sc.nextLine();
                    System.out.print("End Location: ");
                    String end = sc.nextLine();
                    rideService.bookRide(uid, start, end);
                }
                case 4 -> {
                    System.out.print("Ride ID to complete: ");
                    String rid = sc.nextLine();
                    rideService.completeRide(rid);
                }
                case 5 -> rideService.showActiveRides();
                case 6 -> {
                    System.out.print("User ID for history: ");
                    String uid = sc.nextLine();
                    rideService.showRideHistory(uid);
                }
                case 7 -> {
                    System.out.println("Select Pricing Strategy:");
                    System.out.println("1. Standard Fare");
                    System.out.println("2. Surge Fare");
                    System.out.println("3. Discount Fare");
                    System.out.print("Choice: ");
                    int strat = sc.nextInt();
                    sc.nextLine();
                    switch (strat) {
                        case 1 -> rideService.setPricingStrategy(new StandardFare());
                        case 2 -> {
                            System.out.print("Enter surge multiplier: ");
                            double multi = sc.nextDouble();
                            sc.nextLine();
                            rideService.setPricingStrategy(new SurgeFare(multi));
                        }
                        case 3 -> {
                            System.out.print("Enter discount percentage: ");
                            double disc = sc.nextDouble();
                            sc.nextLine();
                            rideService.setPricingStrategy(new DiscountFare(disc));
                        }
                        default -> System.out.println("Invalid strategy choice!");
                    }
                }
                case 8 -> System.out.println("Exiting Ride Booking System...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        sc.close();
    }
}
