package com.example.orderservice.repository;

import com.example.orderservice.entity.PaymentMethodList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentMethodListRepository extends MongoRepository<PaymentMethodList,String> {
}
