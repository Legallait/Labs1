package com.example.Labs1.controller;

import com.example.Labs1.model.Person;
import com.example.Labs1.service.PersonNotFoundException;
import com.example.Labs1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePersonNotFound(PersonNotFoundException e) {
        return Map.of("message", e.getMessage());
    }

    @GetMapping("/persons")
    public List<Person> listOfPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public Person aPerson(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }
}