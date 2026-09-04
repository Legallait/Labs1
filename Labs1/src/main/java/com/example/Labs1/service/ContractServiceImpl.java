package com.example.Labs1.service;

import com.example.Labs1.model.Car;
import com.example.Labs1.model.Contract;
import com.example.Labs1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Contract> getAllContracts() {
        return StreamSupport.stream(contractRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException(id));
    }

    @Override
    @Transactional
    public Contract addContract(Long personId, Long carId, String beginDate, String endDate) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId.toString()));
        return contractRepository.save(new Contract(person, car, beginDate, endDate));
    }

    @Override
    public List<Contract> getContractsByPersonId(Long personId) {
        return contractRepository.findByPersonId(personId);
    }

    @Override
    public List<Contract> getContractsByCarId(Long carId) {
        return contractRepository.findByCarId(carId);
    }
}