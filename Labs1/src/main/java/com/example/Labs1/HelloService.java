package com.example.Labs1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}