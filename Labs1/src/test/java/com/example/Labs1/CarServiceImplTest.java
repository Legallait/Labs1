package com.example.Labs1;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;
import com.example.Labs1.service.CarAlreadyExistsException;
import com.example.Labs1.service.CarNotFoundException;
import com.example.Labs1.service.CarRepository;
import com.example.Labs1.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car ferrari;
    private Car peugeot;

    @BeforeEach
    void setUp() {
        ferrari = new Car("11AA22", "Ferrari", 100);
        peugeot = new Car("33BB44", "Peugeot", 40);
        peugeot.setRented(true);
    }

    @Test
    void getUnrentedCarsOnlyReturnsCarsNotRented() {
        when(carRepository.findAll()).thenReturn(List.of(ferrari, peugeot));

        assertThat(carService.getUnrentedCars()).containsExactly(ferrari);
    }

    @Test
    void getCarByPlateNumberReturnsCarWhenFound() {
        when(carRepository.findByPlateNumber("11AA22")).thenReturn(Optional.of(ferrari));

        assertThat(carService.getCarByPlateNumber("11AA22")).isEqualTo(ferrari);
    }

    @Test
    void getCarByPlateNumberThrowsWhenNotFound() {
        when(carRepository.findByPlateNumber("00ZZ00")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> carService.getCarByPlateNumber("00ZZ00"))
                .isInstanceOf(CarNotFoundException.class);
    }

    @Test
    void rentCarMarksCarAsRentedAndSaves() {
        when(carRepository.findByPlateNumber("11AA22")).thenReturn(Optional.of(ferrari));
        when(carRepository.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Car result = carService.rentCar("11AA22", new Dates("2026-09-10", "2026-09-15"));

        assertThat(result.isRented()).isTrue();
        assertThat(result.getMessage()).isEqualTo("Voiture louée avec succès");
        verify(carRepository).save(ferrari);
    }

    @Test
    void returnCarMarksCarAsNotRentedAndSaves() {
        when(carRepository.findByPlateNumber("33BB44")).thenReturn(Optional.of(peugeot));
        when(carRepository.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Car result = carService.returnCar("33BB44");

        assertThat(result.isRented()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Voiture rendue avec succès");
        verify(carRepository).save(peugeot);
    }

    @Test
    void addCarSavesCarViaRepository() {
        when(carRepository.findByPlateNumber("11AA22")).thenReturn(Optional.empty());
        when(carRepository.save(ferrari)).thenReturn(ferrari);

        Car result = carService.addCar(ferrari);

        assertThat(result).isEqualTo(ferrari);
        verify(carRepository).save(ferrari);
    }

    @Test
    void addCarThrowsWhenPlateNumberAlreadyExists() {
        when(carRepository.findByPlateNumber("11AA22")).thenReturn(Optional.of(ferrari));

        assertThatThrownBy(() -> carService.addCar(ferrari))
                .isInstanceOf(CarAlreadyExistsException.class);
    }
}