package com.example.gatewayservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {
    @Id
    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String city;

    @NotEmpty
    @NotNull
    private String address;

    @NotNull
    private int zipCode;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updated_at;
}
