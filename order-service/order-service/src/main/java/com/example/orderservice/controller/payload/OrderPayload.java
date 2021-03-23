package com.example.orderservice.controller.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class OrderPayload {

    private String order_status;
    @NotNull
    @NotEmpty
    private String payment_status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date due_date;

    private String charge_id;
}
