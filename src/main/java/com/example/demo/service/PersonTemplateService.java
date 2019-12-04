package com.example.demo.service;

import com.couchbase.client.java.view.ViewQuery;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Qualifier("PersonTemplateService")
public class PersonTemplateService implements PersonService {
    private static final String DESIGN_DOC = "person";
    @Autowired
    private CouchbaseTemplate template;

    public Person findOne(String id) {
        return template.findById(id, Person.class);
    }

    public List<Person> findAll() {
        return template.findByView(ViewQuery.from(DESIGN_DOC, "all"), Person.class);
    }

    public List<Person> findByFirstName(String firstName) {
        return template.findByView(ViewQuery.from(DESIGN_DOC, "byFirstName"), Person.class);
    }

    public void create(Person person) {
        person.setCreated(LocalDateTime.now());
        template.insert(person);
    }

    public void update(Person person) {
        person.setUpdated(LocalDateTime.now());
        template.update(person);
    }

    public void delete(Person person) {
        template.remove(person);
    }
}