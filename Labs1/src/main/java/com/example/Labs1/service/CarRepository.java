package com.example.Labs1.service;
import com.example.Labs1.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByPlateNumber(String plateNumber);
}