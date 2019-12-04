package com.example.demo.service;

import com.example.demo.model.Person;

import java.util.List;

public interface PersonService {
    Person findOne(String id);
    List<Person> findAll();
    List<Person> findByFirstName(String firstName);

    void create(Person person);
    void update(Person person);
    void delete(Person person);
}