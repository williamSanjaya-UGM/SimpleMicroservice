package com.example.customerservice.service;

import com.example.customerservice.entity.Address;
import com.example.customerservice.entity.User;
import com.example.customerservice.exception.ResourceNotFoundException;
import com.example.customerservice.repository.AddressRepository;
import com.example.customerservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public User createAddress(String userId, Address address){
        return userRepository.findById(userId).map(user -> {
            user.setAddress(address);
            address.setUser(user);
            return userRepository.save(user);
        }).orElseThrow(()->new ResourceNotFoundException("userId "+ userId+" not found"));
    }

    public User updateAddress(String userId, Address addressRequest){
        return userRepository.findById(userId).map(user -> {
            user.getAddress().setCity(addressRequest.getCity());
            user.getAddress().setAddress(addressRequest.getAddress());
            user.getAddress().setZipCode(addressRequest.getZipCode());
            return userRepository.save(user);
        }).orElseThrow(()->new ResourceNotFoundException("userId "+ userId+" not found"));
    }

    public ResponseEntity<?> deleteAddress(String userId) {
        return addressRepository.findById(userId).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("userId "+ userId+" not found"));
    }
}
