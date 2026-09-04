package com.example.Labs1;

import com.example.Labs1.service.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=dev")
class DemoDataInitializationTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void demoBeanSeedsThreeCarsAtStartup() {
        assertThat(carRepository.findAll()).hasSize(3);
    }
}