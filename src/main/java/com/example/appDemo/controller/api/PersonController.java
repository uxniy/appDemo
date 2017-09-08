package com.example.appDemo.controller.api;

import com.example.appDemo.model.Person;
import com.example.appDemo.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xu.yin on 8/24/17.
 */
@RestController
@RequestMapping("/api/people")
public class PersonController {
    @Autowired
    PeopleRepository peopleRepository;

    @GetMapping
    public List<Person> findAll(){
        return  peopleRepository.findAll();
    }

    @GetMapping(value = "name/{name}")
    public List<Person> findByName(@PathVariable String name){
        return peopleRepository.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person){
        return peopleRepository.save(person);
    }

    @GetMapping(value = "test")
    public String findOne(){
        return new Person("Test",100).toString();
    }

    @PutMapping(value = "id/{id}")
    public  Person update(@PathVariable Long id, @RequestBody Person person){
        person.setId(id);
        return peopleRepository.save(person);

    }



}
