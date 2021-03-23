package com.example.cartservice.repository;

import com.example.cartservice.entity.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CartRepository {
    private static Logger logger = LoggerFactory.getLogger(CartRepository.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Cart save(String userEmail, Cart cart) {
        logger.info("cek masuk cart "+ cart);
        redisTemplate.opsForHash().put(userEmail,cart.getProductId(),cart);
        logger.info("isi "+cart);
        return cart;
    }

    public List<?> findAll(String userEmail){
        return redisTemplate.opsForHash().values(userEmail);
    }

    public Cart updateProductInCart(String userEmail, Cart cart) {
        redisTemplate.opsForHash().put(userEmail,cart.getProductId(),cart);
        return cart;
    }

    public ResponseEntity<?> deleteProductInCart(String userEmail, String productId) {
        redisTemplate.opsForHash().delete(userEmail,productId);
        return ResponseEntity.ok().body("Product deleted successfully");
    }

    public ResponseEntity<?> deleteKey(String userEmail) {
        redisTemplate.delete(userEmail);
        return ResponseEntity.ok().body("keyId deleted successfully");
    }
}
