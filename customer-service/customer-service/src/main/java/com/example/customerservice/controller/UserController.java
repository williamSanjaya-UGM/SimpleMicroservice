package com.example.customerservice.controller;

import com.example.customerservice.entity.User;
import com.example.customerservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public User registerUser(@Valid @RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody User user){
        return userService.loginUser(user);
    }

    @GetMapping("/{email}")
    public Optional<User> findUserByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }
}
