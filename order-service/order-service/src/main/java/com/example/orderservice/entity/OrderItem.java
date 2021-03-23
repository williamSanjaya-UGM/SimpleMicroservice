package com.example.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private String productId;
    private String productName;
    private int qty;
    private int unit_in_price;
    private int discount_in_rupee;
}
