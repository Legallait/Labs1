package com.example.Labs1.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(Long id) {
        super("Contract not found: " + id);
    }
}