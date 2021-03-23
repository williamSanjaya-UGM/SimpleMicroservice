package com.example.customerservice.service;

import com.example.customerservice.entity.User;
import com.example.customerservice.exception.ResourceNotFoundException;
import com.example.customerservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public ResponseEntity<?> loginUser(User user) {
        Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
        if(userOpt.isPresent()) {
            User existingUser = userOpt.get();
            if(user.getPassword().equals(existingUser.getPassword())) {
                return ResponseEntity.ok().build();
            }
        }
        throw new RuntimeException("Username not exist");
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
