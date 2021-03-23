package com.example.cartservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("cart")
public class Cart implements Serializable {
    @Id
    private String productId;
    private String productName;
    private int qty;
    private int unit_in_price;
    private int discount_in_rupee;
}
