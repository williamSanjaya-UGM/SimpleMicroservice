package com.example.customersupportservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personInCharges")
public class PersonInCharge {

    @Id
    private String id;

    @Indexed(unique = true)
    private String personId;

    @Indexed(unique = true)
    private String personInChargeName;

    @Indexed(unique = true)
    private Set<CustomerDetail> customerDetails;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Date created_at;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Date updated_at;
}
