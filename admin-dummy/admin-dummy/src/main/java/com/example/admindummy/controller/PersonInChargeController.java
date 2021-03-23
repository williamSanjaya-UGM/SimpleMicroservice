package com.example.admindummy.controller;

import com.example.admindummy.entity.PersonInCharge;
import com.example.admindummy.repository.PersonInChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class PersonInChargeController {

    @Autowired
    private PersonInChargeRepository personInChargeRepository;

    @PostMapping
    public PersonInCharge addPerson(@RequestBody PersonInCharge personInCharge){
        return personInChargeRepository.save(personInCharge);
    }

    @GetMapping("/{personId}")
    public Optional<PersonInCharge> getById(@PathVariable String personId){
        return personInChargeRepository.findById(personId);
    }
}
