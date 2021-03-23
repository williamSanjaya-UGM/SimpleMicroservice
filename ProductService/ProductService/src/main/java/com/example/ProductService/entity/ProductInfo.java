package com.example.ProductService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productInfo")
public class ProductInfo {

    @Id
    @Column(name="product_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    private int product_damaged=0;

    private int product_returned=0;

    private int product_sold=0;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false,updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updated_at;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
}
