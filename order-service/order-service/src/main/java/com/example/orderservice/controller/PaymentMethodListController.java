package com.example.orderservice.controller;

import com.example.orderservice.entity.Payment;
import com.example.orderservice.entity.PaymentMethodList;
import com.example.orderservice.repository.PaymentMethodListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PaymentMethodListController {

    @Autowired
    private PaymentMethodListRepository paymentMethodListRepository;

    @GetMapping("/payment-list")
    public List<PaymentMethodList> getAllPaymentMethodLists(){
        return paymentMethodListRepository.findAll();
    }
}
