package com.example.Labs1.service;

import com.example.Labs1.model.Contract;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractRepository extends CrudRepository<Contract, Long> {
    List<Contract> findByPersonId(Long personId);

    List<Contract> findByCarId(Long carId);
}