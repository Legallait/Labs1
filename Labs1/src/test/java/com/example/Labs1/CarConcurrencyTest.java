package com.example.Labs1;

import com.example.Labs1.model.Car;
import com.example.Labs1.service.CarRepository;
import com.example.Labs1.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class CarConcurrencyTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Test
    void onlyOneOfTwoConcurrentAddCarSucceeds() throws InterruptedException {
        String plateNumber = "99XX99";
        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        AtomicInteger successCount = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            ready.countDown();
            try {
                start.await();
                carService.addCar(new Car(plateNumber, "Tesla", 80));
                successCount.incrementAndGet();
            } catch (Exception ignored) {
            }
        };

        executor.submit(task);
        executor.submit(task);
        ready.await();
        start.countDown();
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertThat(successCount.get()).isEqualTo(1);
        assertThat(carRepository.findByPlateNumber(plateNumber)).isPresent();
    }
}