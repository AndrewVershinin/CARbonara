package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private final String filePath = "CarDealership/src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(filePath, true));

            Vehicle v = contract.getVehicleSold();
            String line = "";

            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;

                line = "SALE" +
                        sc.getDate() + "|" +
                        sc.getCustomerName() + "|" +
                        sc.getCustomerEmail() + "|" +
                        v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        String.format("%.2f", sc.getPrice()) + "|" +
                        String.format("%.2f", sc.getSalesTax()) + "|" +
                        String.format("%.2f", sc.getRecordingFee()) + "|" +
                        String.format("%.2f", sc.getProcessingFee()) + "|" +
                        String.format("%.2f", sc.getTotalPrice()) + "|" +
                        (sc.isFinance() ? "YES" : "NOT") + "|" +
                        String.format("%.2f", sc.getMonthlyPayment());

            } else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;

                line = "LEASE|" +
                        lc.getDate() + "|" +
                        lc.getCustomerName() + "|" +
                        lc.getCustomerEmail() + "|" +
                        v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        String.format("%.2f", lc.getPrice()) + "|" +
                        String.format("%.2f", lc.getExpectedEndingValue()) + "|" +
                        String.format("%.2f", lc.getLeaseFee()) + "|" +
                        String.format("%.2f", lc.getTotalPrice()) + "|" +
                        String.format("%.2f", lc.getMonthlyPayment());
            }

            bufWriter.write(line);
            bufWriter.newLine();
            bufWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
