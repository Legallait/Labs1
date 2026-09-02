package com.example.Labs1.service;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getUnrentedCars() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false)
                .filter(car -> !car.isRented())
                .collect(Collectors.toList());
    }

    @Override
    public Car getCarByPlateNumber(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber)
                .orElseThrow(() -> new CarNotFoundException(plateNumber));
    }

    @Override
    public Car rentCar(String plateNumber, Dates dates) {
        Car car = getCarByPlateNumber(plateNumber);
        car.setRented(true);
        car.setRentalDates(dates);
        car.setMessage("Voiture louée avec succès");
        return carRepository.save(car);
    }

    @Override
    public Car returnCar(String plateNumber) {
        Car car = getCarByPlateNumber(plateNumber);
        car.setRented(false);
        car.setRentalDates(null);
        car.setMessage("Voiture rendue avec succès");
        return carRepository.save(car);
    }
}