package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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

    @JsonIgnore
    private Date created_at;

    @JsonIgnore
    private Date updated_at;

    @JsonIgnore
    private WarehouseDto warehouse;

    @JsonIgnore
    private ProductInfoDto productInfo;
}
