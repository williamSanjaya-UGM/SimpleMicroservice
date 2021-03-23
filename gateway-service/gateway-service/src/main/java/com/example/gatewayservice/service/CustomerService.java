package com.example.gatewayservice.service;

import com.example.gatewayservice.UserModel.UserRepository;
import com.example.gatewayservice.controller.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> getCustomer(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return restTemplate.getForEntity("http://customer-service/"+email, UserDto.class);
    }
}
