package com.pluralsight;

import java.io.*;

public class DealershipFileManager {
    private final String filePath = "src/main/resources/dealership.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;

            if ((line = br.readLine()) != null) {
                String[] dealer = line.split("\\|");
                String name = dealer[0];
                String address = dealer[1];
                String phone = dealer[2];
                dealership = new Dealership(name, address, phone);
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String type = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

            bw.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bw.newLine();

            for (Vehicle v : dealership.getAllVehicles()) {
                String line = v.getVin() + "|" + v.getYear() + "|"
                        + v.getMake() + "|" + v.getModel()
                        + "|" + v.getVehicleType() + "|" + v.getColor()
                        + "|" + v.getOdometer() + "|" + v.getPrice();
                bw.write(line);
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
