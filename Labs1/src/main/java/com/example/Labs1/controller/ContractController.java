package com.example.Labs1.controller;

import com.example.Labs1.model.Contract;
import com.example.Labs1.model.Dates;
import com.example.Labs1.service.CarNotFoundException;
import com.example.Labs1.service.ContractNotFoundException;
import com.example.Labs1.service.ContractService;
import com.example.Labs1.service.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ContractController {

    @Autowired
    private ContractService contractService;

    @ExceptionHandler({ContractNotFoundException.class, PersonNotFoundException.class, CarNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(RuntimeException e) {
        return Map.of("message", e.getMessage());
    }

    @GetMapping("/contracts")
    public List<Contract> listOfContracts(@RequestParam(required = false) Long personId,
                                           @RequestParam(required = false) Long carId) {
        if (personId != null) {
            return contractService.getContractsByPersonId(personId);
        }
        if (carId != null) {
            return contractService.getContractsByCarId(carId);
        }
        return contractService.getAllContracts();
    }

    @GetMapping("/contracts/{id}")
    public Contract aContract(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @PostMapping("/contracts")
    public Contract addContract(@RequestParam Long personId, @RequestParam Long carId,
                                 @RequestBody Dates dates) {
        return contractService.addContract(personId, carId, dates.getBegin(), dates.getEnd());
    }
}