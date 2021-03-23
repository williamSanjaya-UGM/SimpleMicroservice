package com.example.rediscacheservice.repository;

import com.example.rediscacheservice.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ProductRepository {

    public static final String HASH_KEY="Product";

    @Autowired
    private RedisTemplate template;

//    public List<Product> findAll(){
//        return template.opsForHash().values(HASH_KEY);
//    }

    public Product findProductById(String id){
        log.info("kondisi id "+id);
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }

    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }

    public ResponseEntity<?> deleteKey(String userEmail) {
        template.delete(HASH_KEY);
        return ResponseEntity.ok().body("keyId deleted successfully");
    }

    public void saveAllStream(Product[] payload) throws JsonProcessingException {
        List<Object> collect = Arrays.stream(payload).map(productPayload -> {
            template.opsForHash().put(HASH_KEY,productPayload.getId(),productPayload);
            return null;
        }).collect(Collectors.toList());
    }
}
