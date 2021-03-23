package com.example.gatewayservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Nama harus lebih dari 2 karakter")
    private String username;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String passwordConfirmation;
}
