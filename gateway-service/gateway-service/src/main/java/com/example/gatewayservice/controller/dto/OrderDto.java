package com.example.gatewayservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @Id
    private String id;

    @NotNull
    @NotEmpty
    private String username;

    private String notes;

    private String order_status;

    private int voucher_disc;

    @NotNull
    private int total_purchased;

    private Date createdAt;

    private Date updatedAt;

    @NotNull
    private List<CartDto> orderItems;

    private PaymentDto payment;
}
