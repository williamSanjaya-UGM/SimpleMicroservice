package com.example.ProductService.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String id;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String name;

    private String description;

    @NotNull
    private int quantity_in_units;

    @NotNull
    private int unit_in_price;

    private int discount_in_rupee;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="warehouse_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Warehouse warehouse;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "product")
    @PrimaryKeyJoinColumn
    private ProductInfo productInfo;
}
