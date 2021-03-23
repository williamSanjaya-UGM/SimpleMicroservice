package com.example.cartservice.controller;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/{userEmail}")
    public Cart saveToCart(@PathVariable String userEmail,
                           @RequestBody Cart cart) {
        return cartRepository.save(userEmail,cart);
    }

    @GetMapping("/{userEmail}")
    @Cacheable(key = "#id", value = "Product", unless = "#result.price >1000") // cache the price below 1000
    public List<?> getAllProducts(@PathVariable String userEmail) {
        return cartRepository.findAll(userEmail);
    }

    @PutMapping("/{userEmail}")
    public Cart updateProductInCart(@PathVariable String userEmail,
                                    @RequestBody Cart cart) {
        return cartRepository.updateProductInCart(userEmail,cart);
    }

    @DeleteMapping("/{userEmail}/delete/{productId}")
    public ResponseEntity<?> deleteProductInCart(@PathVariable String userEmail, @PathVariable String productId) {
        return cartRepository.deleteProductInCart(userEmail,productId);
    }

    @DeleteMapping("/{userEmail}")
    public ResponseEntity<?> deleteKey(@PathVariable String userEmail) {
        return cartRepository.deleteKey(userEmail);
    }
}
