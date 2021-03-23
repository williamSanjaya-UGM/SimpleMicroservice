package com.example.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCatalog {

    @PrimaryKey
    private CatalogKey catalogKey;

    @Column(value = "rating")
    private double rating;

    @Column(value = "productName")
    private String productName;

    @Column(value = "num_of_purchased")
    private int num_of_purchased;

    @NotNull
    @Column(value = "unit_in_price")
    private int unit_in_price;

    @Column(value = "discount_in_rupee")
    private int discount_in_rupee;
}
