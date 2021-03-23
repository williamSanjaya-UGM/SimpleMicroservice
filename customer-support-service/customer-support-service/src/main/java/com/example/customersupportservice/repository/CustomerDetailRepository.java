package com.example.customersupportservice.repository;

import com.example.customersupportservice.entity.CustomerDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDetailRepository extends MongoRepository<CustomerDetail,String> {
}
