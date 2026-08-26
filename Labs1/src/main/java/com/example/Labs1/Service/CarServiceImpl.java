package com.example.Labs1.Service;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> cars = new ArrayList<>();

    public CarServiceImpl() {
        cars.add(new Car("11AA22", "Ferrari", 100));
        cars.add(new Car("33BB44", "Peugeot", 40));
        cars.add(new Car("55CC66", "Renault", 35));
    }

    @Override
    public List<Car> getUnrentedCars() {
        return cars.stream().filter(car -> !car.isRented()).collect(Collectors.toList());
    }

    @Override
    public Car getCarByPlateNumber(String plateNumber) throws Exception {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                return car;
            }
        }
        throw new Exception("Car not found: " + plateNumber);
    }

    @Override
    public Car rentCar(String plateNumber, Dates dates) throws Exception {
        Car car = getCarByPlateNumber(plateNumber);
        car.setRented(true);
        car.setRentalDates(dates);
        car.setMessage("Voiture louée avec succès");
        return car;
    }

    @Override
    public Car returnCar(String plateNumber) throws Exception {
        Car car = getCarByPlateNumber(plateNumber);
        car.setRented(false);
        car.setRentalDates(null);
        car.setMessage("Voiture rendue avec succès");
        return car;
    }
}
