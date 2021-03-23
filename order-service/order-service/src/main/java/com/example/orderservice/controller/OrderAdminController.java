package com.example.orderservice.controller;

import com.example.orderservice.controller.payload.OrderPayload;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.Payment;
import com.example.orderservice.entity.PaymentMethodList;
import com.example.orderservice.repository.PaymentMethodListRepository;
import com.example.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@Slf4j
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentMethodListRepository paymentMethodListRepository;

    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable String orderId, @RequestBody OrderPayload orderPayload){
        log.info("isi orderId "+ orderId);
        log.info("isi request "+ orderPayload);
        return orderService.updateOrder(orderId, orderPayload);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(String orderId) {
        return orderService.deleteOrder(orderId);
    }

    // post paymentList
    @PostMapping("/payment-list")
    public PaymentMethodList addPaymentMethod(@RequestBody PaymentMethodList paymentMethodList) {
        return paymentMethodListRepository.save(paymentMethodList);
    }
}
