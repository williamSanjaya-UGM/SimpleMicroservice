package com.example.gatewayservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ReviewKeyDto reviewKey;

    private String username;

    private String comments;

    private int given_rating;
}
