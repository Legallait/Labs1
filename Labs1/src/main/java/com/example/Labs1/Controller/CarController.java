package com.example.Labs1.Controller;

import com.example.Labs1.Service.CarNotFoundException;
import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;
import com.example.Labs1.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCarNotFound(CarNotFoundException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return response;
    }

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listOfCars() {
        return carService.getUnrentedCars();
    }

    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car aCar(@PathVariable("plateNumber") String plateNumber) {
        return carService.getCarByPlateNumber(plateNumber);
    }

    @PutMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car rentCar(@PathVariable("plateNumber") String plateNumber,
                       @RequestParam(value = "rent", required = true) boolean rent,
                       @RequestBody(required = false) Dates dates) {
        if (rent) {
            return carService.rentCar(plateNumber, dates);
        } else {
            return carService.returnCar(plateNumber);
        }
    }
}