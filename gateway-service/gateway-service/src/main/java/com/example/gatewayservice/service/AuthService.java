package com.example.gatewayservice.service;

import com.example.gatewayservice.TokenModel.ConfirmationToken;
import com.example.gatewayservice.TokenModel.ConfirmationTokenRepository;
import com.example.gatewayservice.UserModel.User;
import com.example.gatewayservice.UserModel.UserRepository;
import com.example.gatewayservice.controller.dto.LoginDto;
import com.example.gatewayservice.controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    @Autowired
    public AuthService(UserRepository userRepository,
                       RestTemplate restTemplate,
                       PasswordEncoder passwordEncoder,
                       ConfirmationTokenRepository confirmationTokenRepository,
                       EmailService emailService){
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
    }

    public ResponseEntity<String> doLogin(LoginDto loginDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>();
        map.put("username",loginDto.getUsername());
        map.put("password",loginDto.getPassword());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity("http://customer-service/login/", request, String.class);
    }

    public String addUser(UserDto userDto) {
        boolean exist = userRepository.findByEmail(userDto.getEmail()).isPresent();
        if (exist) {
            throw new RuntimeException("Email already exist");
        } else {
            if(userDto.getPassword().equals(userDto.getPasswordConfirmation())) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                String encodedPassword = passwordEncoder.encode(userDto.getPassword());
                Map<String, String> map = new HashMap<>();
                map.put("username", userDto.getUsername());
                map.put("email", userDto.getEmail());
                map.put("phoneNumber", userDto.getPhoneNumber());
                map.put("password", encodedPassword);

                HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);
                restTemplate.postForEntity("http://customer-service/register/", request, String.class);

                User user = new User();
                user.setEmail(userDto.getEmail());
                user.setPassword(encodedPassword);
                userRepository.save(user);

                ConfirmationToken confirmationToken = new ConfirmationToken(user);

                confirmationTokenRepository.save(confirmationToken);

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Complete Registration!");
                mailMessage.setText("To confirm your account, please click here: " +
                        "http://localhost:8765/confirm-account?token=" + confirmationToken.getConfirmationToken());

                emailService.sendEmail(mailMessage);

                return "email has been sent to "+user.getEmail();
            } else {
                throw new RuntimeException("Please input password and passwordConfirmation correctly");
            }
        }
    }

    public User confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {
            return userRepository.findByEmail(token.getUser().getEmail()).map(user -> {
                user.setActive(true);
                return userRepository.save(user);
            }).orElseThrow(()->new RuntimeException("email not found"));
        } else {
            throw new RuntimeException("Something wrong, link is broken");
        }
    }
}
