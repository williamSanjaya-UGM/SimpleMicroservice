package com.example.gatewayservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    @Id
    private String productId;
    private String productName;
    private int qty;
    private int unit_in_price;
    private int discount_in_rupee;
}
