package com.example.Labs1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    private List<Car> cars = new ArrayList<>();

    public CarController() {
        cars.add(new Car("11AA22", "Ferrari", 100));
        cars.add(new Car("33BB44", "Peugeot", 40));
        cars.add(new Car("55CC66", "Renault", 35));
    }

    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                return car;
            }
        }
        throw new Exception("Car not found: " + plateNumber);
    }

    @PutMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car rentCar(@PathVariable("plateNumber") String plateNumber,
                       @RequestParam(value = "rent", required = true) boolean rent,
                       @RequestBody(required = false) Dates dates) throws Exception {

        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                if (rent) {
                    car.setRented(true);
                    car.setRentalDates(dates);
                    System.out.println("Location du " + dates.getBegin() + " au " + dates.getEnd());
                } else {
                    car.setRented(false);
                    car.setRentalDates(null);
                }
                return car;
            }
        }
        throw new Exception("Car not found or not available: " + plateNumber);
    }

    @PutMapping(value = "/cars/{plateNumber}")
    public void rent(@PathVariable("plateNumber") String plateNumber, @RequestParam(value = "rent", required = true) boolean rent,
            @RequestBody Dates dates) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {

            }
        }
    }
}