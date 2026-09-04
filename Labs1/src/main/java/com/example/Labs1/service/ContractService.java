package com.example.Labs1.service;

import com.example.Labs1.model.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> getAllContracts();

    Contract getContractById(Long id) throws ContractNotFoundException;

    Contract addContract(Long personId, Long carId, String beginDate, String endDate)
            throws PersonNotFoundException, CarNotFoundException;

    List<Contract> getContractsByPersonId(Long personId);

    List<Contract> getContractsByCarId(Long carId);
}