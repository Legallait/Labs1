package com.example.Labs1;

import com.example.Labs1.controller.CarController;
import com.example.Labs1.model.Car;
import com.example.Labs1.model.Dates;
import com.example.Labs1.service.CarNotFoundException;
import com.example.Labs1.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CarService carService;

    @Test
    void listOfCarsReturnsUnrentedCars() throws Exception {
        when(carService.getUnrentedCars()).thenReturn(List.of(new Car("11AA22", "Ferrari", 100)));

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].plateNumber").value("11AA22"))
                .andExpect(jsonPath("$[0].brand").value("Ferrari"));
    }

    @Test
    void aCarReturnsCarByPlateNumber() throws Exception {
        when(carService.getCarByPlateNumber("11AA22")).thenReturn(new Car("11AA22", "Ferrari", 100));

        mockMvc.perform(get("/cars/11AA22"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Ferrari"));
    }

    @Test
    void aCarReturns404WhenNotFound() throws Exception {
        when(carService.getCarByPlateNumber("00ZZ00")).thenThrow(new CarNotFoundException("00ZZ00"));

        mockMvc.perform(get("/cars/00ZZ00"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void rentCarCallsServiceAndReturnsUpdatedCar() throws Exception {
        Car rented = new Car("11AA22", "Ferrari", 100);
        rented.setRented(true);
        rented.setMessage("Voiture louée avec succès");
        when(carService.rentCar(eq("11AA22"), any(Dates.class))).thenReturn(rented);

        mockMvc.perform(put("/cars/11AA22?rent=true")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new Dates("2026-09-10", "2026-09-15"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rented").value(true))
                .andExpect(jsonPath("$.message").value("Voiture louée avec succès"));
    }

    @Test
    void returnCarCallsServiceAndReturnsUpdatedCar() throws Exception {
        Car returned = new Car("11AA22", "Ferrari", 100);
        returned.setMessage("Voiture rendue avec succès");
        when(carService.returnCar("11AA22")).thenReturn(returned);

        mockMvc.perform(put("/cars/11AA22?rent=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rented").value(false))
                .andExpect(jsonPath("$.message").value("Voiture rendue avec succès"));
    }
}