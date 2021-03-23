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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "paymentMethodLists")
public class PaymentMethodList {

    @Id
    private String id;

    @NotNull
    @NotEmpty
    private String payment_method_name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Date created_at;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Date updated_at;
}
