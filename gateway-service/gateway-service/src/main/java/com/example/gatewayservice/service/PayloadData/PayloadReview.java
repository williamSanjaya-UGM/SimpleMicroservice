package com.example.gatewayservice.service.PayloadData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadReview {

    private String productId;
    private String comments;
    private String given_rating;
}
