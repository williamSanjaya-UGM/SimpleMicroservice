package com.example.rediscacheservice.entity.ProductDto;

import com.example.rediscacheservice.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("productInfo")
public class ProductInfo {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    private int product_damaged=0;

    private int product_returned=0;

    private int product_sold=0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date created_at;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updated_at;

    @JsonBackReference
    private Product product;
}
