package com.example.gatewayservice.service.PayloadData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadCart {

    private String productId;
    private int qty;
}
