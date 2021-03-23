package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableBinding(Sink.class)
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @StreamListener("input")
    public void createOrder(Order order){
        orderService.createOrder(order);
    }

    @GetMapping("/{username}/orderById/{orderId}")
    public Optional<Order> findByUsernameAndOrderId(@PathVariable("username") String username,
                                                    @PathVariable("orderId") String orderId){
        return orderService.findByUsernameAndOrderId(username, orderId);
    }

    @GetMapping("/{username}")
    public List<Order> findByUsername(@PathVariable String username){
        return orderService.findByUsername(username);
    }
}
