package com.example.Labs1.service;

import com.example.Labs1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    @Transactional
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
}