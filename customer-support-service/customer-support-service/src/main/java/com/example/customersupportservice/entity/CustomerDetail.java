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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customerDetails")
public class CustomerDetail {

    @Id
    private String userId;

    @NotNull
    @NotEmpty
    private String username;

    @NotEmpty
    @NotNull
    private String feedback_message;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Date created_at;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Date updated_at;
}
