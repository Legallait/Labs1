package com.example.Labs1;

public class Car {
    private String plateNumber;
    private String brand;
    private double price;
    private boolean rented;
    private Dates rentDate;
    public Car(String plateNumber, String brand, double price){
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.rented = false;
    }


    public Car() {
    }

    public Car(String plateNumber, String brand, double price, Dates rentDate ) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.rented = false;
        this.rentDate = rentDate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Dates getRentDate() {
        return rentDate;
    }
    public void setRentalDates(Dates rentDate) {
        this.rentDate = rentDate;
    }
}