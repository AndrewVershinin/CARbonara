package com.pluralsight;

import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String phone, String address, String name) {
        this.phone = phone;
        this.address = address;
        this.name = name;
    }

    public List<Vehicle> getVehiclesByPrice(Double min, Double max) {
        return;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return;
    }

    public List<Vehicle> getAllVehicles() {
        return;
    }

    public void addVehicle() {

    }

    public void removeVehicle() {

    }
}
