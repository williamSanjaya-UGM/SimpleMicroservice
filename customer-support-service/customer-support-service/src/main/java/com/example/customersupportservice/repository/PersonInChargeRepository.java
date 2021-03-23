package com.example.customersupportservice.repository;

import com.example.customersupportservice.entity.PersonInCharge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonInChargeRepository extends MongoRepository<PersonInCharge,String> {

    Optional<PersonInCharge> findByPersonId(String personId);
}
