package com.example.gatewayservice.service;

import com.example.gatewayservice.controller.dto.CartDto;
import com.example.gatewayservice.controller.dto.OrderDto;
import com.example.gatewayservice.service.PayloadData.PayloadOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {

    private Logger logger = LoggerFactory.getLogger(CartService.class);
    private String payment_status;
    private String due_date;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageChannel output;

    private String getPayment_status(String paymentMethod) {
        if(paymentMethod.equals("transfer")) {
            return payment_status="waiting";
        } else
            return payment_status="in debt";
    }

    private String getDue_date(String payment_status) {
        if(payment_status.equals("in debt")) {
            Date date = Date.valueOf(LocalDate.now().plusDays(30));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } else {
            Date date = Date.valueOf(LocalDate.now().plusDays(1));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        }
    }

    private int getTotal_price(CartDto[] cartDtos,int voucher_disc) {
        int cart_total = Arrays.stream(cartDtos).map(cartPayload ->
                cartPayload.getQty() * (cartPayload.getUnit_in_price() - cartPayload.getDiscount_in_rupee()))
                .reduce(0, (num1, num2) -> num1 + num2);
        return cart_total-voucher_disc;
    }

    public Map<String, Object> doCheckout(PayloadOrder payloadOrder) {
        // TODO: clean up code, consider doing topic in cart service
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        CartDto[] cartDtos = restTemplate.getForObject("http://cart-service/" + email, CartDto[].class);
        int total_price = getTotal_price(cartDtos,payloadOrder.getVoucher_disc());

        String payment_status = getPayment_status(payloadOrder.getPayment_method());
        String due_date = getDue_date(payment_status);

        // For a moment: payment_method: transfer or COD
        Map<String,Object> payments = new HashMap<>();
        payments.put("payment_method",payloadOrder.getPayment_method());
        payments.put("payment_status",payment_status);
        payments.put("due_date",due_date);

        String order_status;
        if(payloadOrder.getPayment_method().equals("transfer")) {
            order_status="waiting for payment";
        } else {
            order_status="processed";
        }

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("username", email);
        orderMap.put("notes", payloadOrder.getNotes());
        orderMap.put("order_status", order_status);
        orderMap.put("voucher_disc",payloadOrder.getVoucher_disc());
        orderMap.put("total_purchased",total_price);
        orderMap.put("orderItems",cartDtos);
        orderMap.put("payment",payments);

        output.send(MessageBuilder.withPayload(orderMap).build());

        restTemplate.delete("http://cart-service/"+email);
        return orderMap;
    }

    public ResponseEntity<OrderDto[]> allOrderByUsername() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return restTemplate.getForEntity("http://order-service/"+email,OrderDto[].class);
    }

    public OrderDto findOrderById(String id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return restTemplate.getForObject("http://order-service/"+email+"/orderById/"+id,OrderDto.class);
    }
}
