package com.dxc.learning.demo1.controller;

import java.time.LocalDate;
import java.util.List;

import com.dxc.learning.demo1.dto.PersonDto;
import com.dxc.learning.demo1.model.Person;
import com.dxc.learning.demo1.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    public void checkExamMapping() {
        PersonDto creation = new PersonDto();
        creation.setName("name");
        creation.setEmail("email");
}
    @Autowired
    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person")
    List<Person> all() {
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    Person one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PostMapping("/Person")
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @PutMapping("/person/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Integer id) {

        return repository.findById(id)
                .map(Person -> {
                    Person.setName(newPerson.getName());
                    Person.setEmail(newPerson.getEmail());
                    Person.setDob(LocalDate.now());
                    return repository.save(Person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/person/{id}")
    void deletePerson(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
