package com.example.Labs1.Controller;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;
import com.example.Labs1.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listOfCars() {
        return carService.getUnrentedCars();
    }

    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
        return carService.getCarByPlateNumber(plateNumber);
    }

    @PutMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car rentCar(@PathVariable("plateNumber") String plateNumber,
                        @RequestParam(value = "rent", required = true) boolean rent,
                        @RequestBody(required = false) Dates dates) throws Exception {
        if (rent) {
            return carService.rentCar(plateNumber, dates);
        } else {
            return carService.returnCar(plateNumber);
        }
    }
}