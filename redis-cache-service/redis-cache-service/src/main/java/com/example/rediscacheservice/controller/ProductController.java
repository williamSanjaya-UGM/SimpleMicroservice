package com.example.rediscacheservice.controller;

import com.example.rediscacheservice.entity.Product;
import com.example.rediscacheservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableBinding(Sink.class)
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping
//    public List<Product> getAllProducts() throws InterruptedException {
//        log.info("cek hit");
//        return productService.getAllProducts();
//    }

    @GetMapping("/{id}")
    public Product findByProductId(@PathVariable String id) throws InterruptedException {
        return productService.findProductById(id);
    }

    @StreamListener("input")
    public void saveAllStream(Product[] products) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String stringProducts = mapper.writeValueAsString(products);
        log.info("payload:: "+stringProducts);
        Product[] payload = mapper.readValue(stringProducts, Product[].class);
        productService.saveAllStream(payload);
    }
}
