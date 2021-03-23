package com.example.gatewayservice.PaymentGateway.controller;

import com.example.gatewayservice.PaymentGateway.commons.Response;
import com.example.gatewayservice.PaymentGateway.service.StripeService;
import com.example.gatewayservice.controller.dto.CartDto;
import com.example.gatewayservice.controller.dto.OrderDto;
import com.example.gatewayservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class PaymentController {

    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    @Autowired
    private RestTemplate restTemplate;

    private StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @GetMapping("/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }

    @PostMapping("/create-charge")
    public  @ResponseBody
    Response createCharge( String orderId, String token, String email) {

        OrderDto orderById = restTemplate.getForObject("http://order-service/" + email + "/orderById/" + orderId, OrderDto.class);
        int paymentAmount = orderById.getTotal_purchased();
        String payment_method=orderById.getPayment().getPayment_method();

        if (payment_method.equals("transfer")) {
            if (API_PUBLIC_KEY == null) {
                return new Response(false, "Stripe payment token is missing. Please, try again later.");
            }

            //create charge
            String chargeId = stripeService.createCharge(email, token, paymentAmount);
            if (chargeId == null) {
                return new Response(false, "An error occurred while trying to create a charge.");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String,Object> updateOrderPaymentMap = new HashMap<>();
            updateOrderPaymentMap.put("order_status","processed");
            updateOrderPaymentMap.put("payment_status","paid off");
            updateOrderPaymentMap.put("due_date",null);
            updateOrderPaymentMap.put("charge_id",chargeId);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(updateOrderPaymentMap, headers);
            restTemplate.exchange("http://order-service/admin/"+orderId, HttpMethod.PUT, request, OrderDto.class);

            return new Response(true, "Success! Your charge id is " + chargeId);
        } else
            return new Response(false, "Sorry your payment method is not transfer ");
    }
}
