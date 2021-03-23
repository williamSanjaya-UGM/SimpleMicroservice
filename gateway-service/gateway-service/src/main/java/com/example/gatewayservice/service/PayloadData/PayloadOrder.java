package com.example.gatewayservice.service.PayloadData;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayloadOrder {
    private String notes;
    private int voucher_disc;
    private String payment_method;
}
