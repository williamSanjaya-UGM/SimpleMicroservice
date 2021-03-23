package com.example.ProductService.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String city;

    @NotEmpty
    @NotNull
    private String address;

    @NotNull
    private int zipCode;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdAt",nullable = false,updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updatedAt",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updated_at;

}
