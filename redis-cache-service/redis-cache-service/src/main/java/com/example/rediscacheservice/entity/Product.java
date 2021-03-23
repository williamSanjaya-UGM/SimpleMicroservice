package com.example.rediscacheservice.entity;

import com.example.rediscacheservice.entity.ProductDto.ProductInfo;
import com.example.rediscacheservice.entity.ProductDto.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("products")
public class Product implements Serializable {

    @Id
    private String id;
    private String name;

    private String description;

    @JsonIgnore
    private int quantity_in_units;

    private int unit_in_price;

    private int discount_in_rupee;

    @JsonIgnore
    private Date created_at;

    @JsonIgnore
    private Date updated_at;

    private Warehouse warehouse;

    @JsonIgnore
    private ProductInfo productInfo;
}
