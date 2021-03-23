package com.example.admindummy.repository;

import com.example.admindummy.entity.PersonInCharge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonInChargeRepository extends MongoRepository<PersonInCharge,String> {
}
