package com.example.ProductService.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @Id
    private String productId;
    private String productName;
    private int qty;
    private int unit_in_price;
    private int discount_in_rupee;
}
