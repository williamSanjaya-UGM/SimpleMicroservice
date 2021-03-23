package com.example.gatewayservice.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {

    private String email;
    private String password;
}
