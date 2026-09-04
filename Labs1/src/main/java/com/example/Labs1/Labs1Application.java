package com.example.Labs1;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Person;
import com.example.Labs1.service.CarService;
import com.example.Labs1.service.ContractService;
import com.example.Labs1.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Labs1Application {

    public static void main(String[] args) {
        SpringApplication.run(Labs1Application.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner demo(CarService carService, PersonService personService, ContractService contractService) {
        return (args) -> {
            Car ferrari = carService.addCar(new Car("11AA22", "Ferrari", 100));
            Car peugeot = carService.addCar(new Car("33BB44", "Peugeot", 40));
            carService.addCar(new Car("55CC66", "Renault", 35));

            Person alice = personService.addPerson(new Person("Alice", "Martin"));
            Person bob = personService.addPerson(new Person("Bob", "Dupont"));

            contractService.addContract(alice.getId(), ferrari.getId(), "2026-09-10", "2026-09-15");
            contractService.addContract(bob.getId(), peugeot.getId(), "2026-09-12", "2026-09-20");
        };
    }
}