package com.example.rediscacheservice.service;

import com.example.rediscacheservice.entity.Product;
import com.example.rediscacheservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @Cacheable(value = "fifty-seconds-cache", key = "#root.methodName")
//    public List<Product> getAllProducts() throws InterruptedException {
////        Thread.sleep(4000);
//        return productRepository.findAll();
//    }

    @CacheEvict(value = "fifty-seconds-cache", allEntries = true, beforeInvocation = true)
    public void saveAllStream(Product[] products) throws JsonProcessingException {
        productRepository.saveAllStream(products);
    }

    @Cacheable(value = "fifty-seconds-cache",key = "'ProductInCache'+#id")
    public Product findProductById(String id) throws InterruptedException {
//        Thread.sleep(4000);
        return productRepository.findProductById(id);
    }
}
