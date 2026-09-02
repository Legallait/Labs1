package com.example.Labs1;

import com.example.Labs1.model.Car;
import com.example.Labs1.service.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void savesAndFindsCarByPlateNumber() {
        carRepository.save(new Car("11AA22", "Ferrari", 100));

        Optional<Car> found = carRepository.findByPlateNumber("11AA22");

        assertThat(found).isPresent();
        assertThat(found.get().getBrand()).isEqualTo("Ferrari");
        assertThat(found.get().getId()).isNotNull();
    }

    @Test
    void unknownPlateNumberReturnsEmpty() {
        Optional<Car> found = carRepository.findByPlateNumber("00ZZ00");

        assertThat(found).isEmpty();
    }
}