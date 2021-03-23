package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findByUsernameOrderByCreatedAtDesc(String name);

    Optional<Order> findByUsernameAndId(String name, String id);
}
