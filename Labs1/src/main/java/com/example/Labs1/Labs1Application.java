package com.example.Labs1;

import com.example.Labs1.model.Car;
import com.example.Labs1.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Labs1Application {

    public static void main(String[] args) {
        SpringApplication.run(Labs1Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CarService carService) {
        return (args) -> {
            carService.addCar(new Car("11AA22", "Ferrari", 100));
            carService.addCar(new Car("33BB44", "Peugeot", 40));
            carService.addCar(new Car("55CC66", "Renault", 35));
        };
    }
}