package com.example.gatewayservice.controller;

import com.example.gatewayservice.controller.dto.CartDto;
import com.example.gatewayservice.service.CartService;
import com.example.gatewayservice.service.PayloadData.PayloadCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<?> saveToCart(@RequestBody PayloadCart payload){
        log.info("kondisi payload "+ payload);
        return cartService.saveToCart(payload);
    }

    @GetMapping
    public ResponseEntity<CartDto[]> getAllProducts() {
        return cartService.getAllProducts();
    }

    @PutMapping
    public ResponseEntity<?> updateProductInCart(@RequestBody PayloadCart payload){
        return cartService.updateProductInCart(payload);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProductInCart(@PathVariable String productId) {
        return cartService.deleteProductInCart(productId);
    }
}
