package com.example.gatewayservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

public class ProductInfoDto {

    @Id
    private String id;

    private int product_damaged=0;

    private int product_returned=0;

    private int product_sold=0;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updated_at;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @MapsId
    @JoinColumn(name = "product_id")
    private ProductDto product;
}
