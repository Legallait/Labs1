package com.example.Labs1.Service;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;

import java.util.List;

public interface CarService {
    List<Car> getUnrentedCars();

    Car getCarByPlateNumber(String plateNumber) throws Exception;

    Car rentCar(String plateNumber, Dates dates) throws Exception;

    Car returnCar(String plateNumber) throws Exception;
}