package com.example.Labs1.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException(String plateNumber) {
        super("Car already exists: " + plateNumber);
    }
}