package com.dxc.learning.demo1.controller;

import java.util.List;
import javax.validation.Valid;
import com.dxc.learning.demo1.dto.PersonDto;
import com.dxc.learning.demo1.model.Person;
import com.dxc.learning.demo1.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ModelMapper modelMapper;

    @Autowired
    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping({"","/"})
    public ResponseEntity<PersonDto> postMethodName(@Valid @RequestBody PersonDto personDto){
        Person person = modelMapper.map(personDto, Person.class);
        return ResponseEntity.ok(personDto);
    }

    @GetMapping("/person")
    public ResponseEntity<Person> getPersonById(@PathVariable Person person){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping({"","/"})
    List<Person> all() {
        return repository.findAll();
    }

    @PostMapping("/person")
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @PutMapping("/person/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Integer id) {

        return repository.findById(id)
                .map(Person -> {
                    Person.setName(newPerson.getName());
                    Person.setEmail(newPerson.getEmail());
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