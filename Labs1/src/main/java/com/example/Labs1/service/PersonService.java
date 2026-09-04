package com.example.Labs1.service;

import com.example.Labs1.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    Person getPersonById(Long id) throws PersonNotFoundException;

    Person addPerson(Person person);
}