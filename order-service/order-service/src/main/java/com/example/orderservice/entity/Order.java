package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @NotNull
    @NotEmpty
    private String username;

    private String notes;

    private String order_status;

    private int voucher_disc;

    @NotNull
    private int total_purchased;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Date updatedAt;

    @NotNull
    private List<OrderItem> orderItems;

    private Payment payment;
}
