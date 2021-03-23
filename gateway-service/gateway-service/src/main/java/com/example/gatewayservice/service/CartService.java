package com.example.gatewayservice.service;

import com.example.gatewayservice.controller.dto.CartDto;
import com.example.gatewayservice.controller.dto.ProductDto;
import com.example.gatewayservice.service.PayloadData.PayloadCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    private static Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> saveToCart(PayloadCart payload){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        ProductDto productDto = restTemplate.getForObject("http://redis-cache-service/products/" + payload.getProductId(), ProductDto.class);
        String productName= productDto.getName();
        int unit_in_price= productDto.getUnit_in_price();
        int discount_in_rupee= productDto.getDiscount_in_rupee();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("productId", payload.getProductId());
        map.put("productName", productName);
        map.put("qty", payload.getQty());
        map.put("unit_in_price",unit_in_price);
        map.put("discount_in_rupee",discount_in_rupee);
        logger.info("request:: "+map);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
        logger.info("cek request "+map);
        return restTemplate.postForEntity("http://cart-service/"+email, request, CartDto.class);
    }

    public ResponseEntity<CartDto[]> getAllProducts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return restTemplate.getForEntity("http://cart-service/"+email,CartDto[].class);
    }

    public ResponseEntity<?> updateProductInCart(PayloadCart payload) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        ProductDto productDto = restTemplate.getForObject("http://redis-cache-service/products/" + payload.getProductId(), ProductDto.class);
        String productName= productDto.getName();
        int unit_in_price= productDto.getUnit_in_price();
        int discount_in_rupee= productDto.getDiscount_in_rupee();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("productId", payload.getProductId());
        map.put("productName", productName);
        map.put("qty", payload.getQty());
        map.put("unit_in_price",unit_in_price);
        map.put("discount_in_rupee",discount_in_rupee);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
        restTemplate.put("http://cart-service/"+email, request, CartDto.class);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteProductInCart(String productId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        restTemplate.delete("http://cart-service/"+email+"/delete/"+productId);
        return ResponseEntity.ok().body("Product deleted successfully");
    }
}
