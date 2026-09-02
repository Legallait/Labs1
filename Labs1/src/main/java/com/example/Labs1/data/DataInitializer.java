package com.example.Labs1.data;

import com.example.Labs1.model.Car;
import com.example.Labs1.service.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
        @Bean
        CommandLineRunner initCars(CarRepository carRepository) {
        return args -> {
            carRepository.save(new Car("11AA22", "Ferrari", 100));
            carRepository.save(new Car("33BB44", "Peugeot", 40));
            carRepository.save(new Car("55CC66", "Renault", 35));
        };
    }
}
