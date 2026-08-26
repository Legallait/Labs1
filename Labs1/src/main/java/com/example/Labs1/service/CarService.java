package com.example.Labs1.service;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;

import java.util.List;

public interface CarService {
    List<Car> getUnrentedCars();

    Car getCarByPlateNumber(String plateNumber) throws CarNotFoundException;

    Car rentCar(String plateNumber, Dates dates);

    Car returnCar(String plateNumber);
}