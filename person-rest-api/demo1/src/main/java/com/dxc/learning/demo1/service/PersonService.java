package com.dxc.learning.demo1.service;

import com.dxc.learning.demo1.model.Person;

import java.util.List;

public interface PersonService {

    // Create a person
    // Find a person by id
    // Find all people
    // Update a person
    // Delete a person

    public Person getPersonId(int id);

    public Person createPerson(Person Person);

    public Person updatePerson(int id);

    public void deletePerson(int id);

    public List<Person> getAllPerson();
}
