package com.example.gatewayservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotEmpty
    @NotNull
    private String username;

    @NotEmpty
    @NotNull
    private String password;
}
