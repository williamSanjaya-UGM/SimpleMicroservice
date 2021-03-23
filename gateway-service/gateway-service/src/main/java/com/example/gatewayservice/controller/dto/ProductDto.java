package com.example.gatewayservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Id
    private String id;

    @NotEmpty
    @NotNull
    private String name;

    private String description;

    @NotNull
    private int quantity_in_units;

    @NotNull
    private int unit_in_price;

    private int discount_in_rupee;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updated_at;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="warehouse_id",nullable = false)
    private WarehouseDto warehouse;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "product")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private ProductInfoDto productInfo;
}
