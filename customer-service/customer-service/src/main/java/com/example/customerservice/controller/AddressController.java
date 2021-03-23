package com.example.customerservice.controller;

import com.example.customerservice.entity.Address;
import com.example.customerservice.entity.User;
import com.example.customerservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/{userId}/address")
    public User createAdddress(@PathVariable("userId") String userId, @Valid @RequestBody Address address){
        return addressService.createAddress(userId,address);
    }

    @PutMapping("/{userId}/address")
    public User updateAddress(@PathVariable("userId") String userId, @Valid @RequestBody Address addressRequest){
        return addressService.updateAddress(userId,addressRequest);
    }

    @DeleteMapping("/{userId}/address")
    public ResponseEntity<?> deleteAddress(@PathVariable("userId") String userId) {
        return addressService.deleteAddress(userId);
    }
}
