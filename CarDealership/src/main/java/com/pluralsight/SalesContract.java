package com.pluralsight;

public class SalesContract extends Contract {
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
    }

    public boolean isFinance() {
        return finance;
    }

    @Override
    public double getTotalPrice() {
        double tax = vehicleSold.getPrice() * 0.05;
        double recordingFee = 100;
        double processingFee = vehicleSold.getPrice() < 1000 ? 295 : 495;
        return vehicleSold.getPrice() + tax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
       if (!finance) return 0;

       double totalPrice = getTotalPrice();
       int months;
       double rate;

       if (vehicleSold.getPrice() >= 10000) {
           months = 48;
           rate = 0.0425;
       } else {
           months = 24;
           rate = 0.0525;
       }
       // 1 + rate: we just add the interest to the original price
       return totalPrice * (1 + rate) / months;
    }
}
