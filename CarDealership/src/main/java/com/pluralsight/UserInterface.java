package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager fileManager = new DealershipFileManager();

    public void display() {
        init();

        Scanner input = new Scanner(System.in);
        int choice = -1;

        while (choice != 99) {
            displayMenu();
            System.out.print("Enter your choice: ");

            if (input.hasNextLine()) {
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        processGetByPriceRequest(input);
                        break;
                    case 2:
                        processGetByMakeModelRequest(input);
                        break;
                    case 3:
                        processGetByYearRequest(input);
                        break;
                    case 4:
                        processGetByColorRequest(input);
                        break;
                    case 5:
                        processGetByMileageRequest(input);
                        break;
                    case 6:
                        processGetByVehicleTypeRequest(input);
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        processAddVehicleRequest(input);
                        break;
                    case 9:
                        processRemoveVehicleRequest(input);
                        break;
                    case 99:
                        System.out.println("Goodbye!");
                        break;
                }
            }
        }
    }

    public void init() {
        this.dealership = fileManager.getDealership();
    }

    public void displayMenu() {
        System.out.println("\n--- Dealership Menu ---");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make/model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
    }

    public void processGetByPriceRequest(Scanner input) {
        System.out.println("Enter minimum price: ");
        double minPrice = input.nextDouble();
        input.nextLine();

        System.out.println("Enter maximum price: ");
        double maxPrice = input.nextDouble();
        input.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);

        displayVehicles(filteredVehicles);
    }

    public void processGetByMakeModelRequest(Scanner input) {
        System.out.print("Enter make: ");
        String make = input.nextLine();

        System.out.print("Enter model: ");
        String model = input.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByMakeModel(make, model);

        displayVehicles(filteredVehicles);
    }

    public void processGetByYearRequest(Scanner input) {
        System.out.print("Enter minimum year: ");
        int minYear = input.nextInt();
        input.nextLine();

        System.out.print("Enter maximum year: ");
        int maxYear = input.nextInt();
        input.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(filteredVehicles);
    }

    public void processGetByColorRequest(Scanner input) {
        System.out.print("Enter color: ");
        String color = input.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByColor(color);

        displayVehicles(filteredVehicles);
    }

    public void processGetByMileageRequest(Scanner input) {
        System.out.print("Enter minimum mileage: ");
        int minMileage = input.nextInt();
        input.nextLine();

        System.out.print("Enter maximum mileage: ");
        int maxMileage = input.nextInt();
        input.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);

        displayVehicles(filteredVehicles);
    }

    public void processGetByVehicleTypeRequest(Scanner input) {
        System.out.print("Enter vehicle type (e.g., SUV, Sedan, Truck): ");
        String type = input.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByType(type);

        displayVehicles(filteredVehicles);
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();

        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest(Scanner input) {
        System.out.print("Enter VIN (number): ");
        int vin = input.nextInt();
        input.nextLine();

        System.out.print("Enter year: ");
        int year = input.nextInt();
        input.nextLine();

        System.out.print("Enter make: ");
        String make = input.nextLine();

        System.out.print("Enter model: ");
        String model = input.nextLine();

        System.out.print("Enter type: ");
        String type = input.nextLine();

        System.out.print("Enter color: ");
        String color = input.nextLine();

        System.out.print("Enter odometer reading: ");
        int odometer = input.nextInt();
        input.nextLine();

        System.out.print("Enter price: ");
        double price = input.nextDouble();
        input.nextLine();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(newVehicle);
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added to inventory!");
    }

    public void processRemoveVehicleRequest(Scanner input) {
        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = input.nextInt();
        input.nextLine();

        List<Vehicle> allVehicles = dealership.getAllVehicles();
        Vehicle vehicleToRemove = null;

        for (Vehicle v : allVehicles) {
            if (v.getVin() == vin) {
                vehicleToRemove = v;
                break;
            }
        }
        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed from inventory");
        } else {
            System.out.println("Vehicle not found");
        }
    }

    public void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v.getVin() + "|" + v.getYear() + "|"
                        + v.getMake() + "|" + v.getModel()
                        + "|" + v.getVehicleType() + "|" + v.getColor()
                        + "|" + v.getOdometer() + "|" + v.getPrice());
            }
        }
    }
}

