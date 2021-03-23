package com.example.gatewayservice.controller;

import com.example.gatewayservice.UserModel.User;
import com.example.gatewayservice.UserModel.UserRepository;
import com.example.gatewayservice.controller.dto.LoginDto;
import com.example.gatewayservice.controller.dto.UserDto;
import com.example.gatewayservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<String> doLogin(@RequestBody LoginDto loginDto){
        return authService.doLogin(loginDto);
    }

    @PostMapping("register")
    public String addUser(@RequestBody UserDto userDto) {
        return authService.addUser(userDto);
    }

    @GetMapping("confirm-account")
    public User confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return authService.confirmUserAccount(confirmationToken);
    }
}
