package com.example.Labs1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String plateNumber;

    private String brand;

    private double price;

    private boolean rented;

    @Transient
    @JsonIgnore
    private Dates rentDate;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public Car(String plateNumber, String brand, double price) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.rented = false;
    }

    public Car() {
    }

    public Car(String plateNumber, String brand, double price, Dates rentDate) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.rented = false;
        this.rentDate = rentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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