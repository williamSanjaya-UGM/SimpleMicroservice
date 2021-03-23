package com.example.ProductService.controller;

import com.example.ProductService.dto.OrderDto;
import com.example.ProductService.entity.Product;
import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.service.ProductService;
import com.example.ProductService.service.payload.OrderItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@EnableBinding(Sink.class)
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> findProductById(@PathVariable String id) {
        return productService.findProductById(id);
    }

    @StreamListener("input")
    public void updateProductsById(OrderDto orderDto) {
        log.info("cek isi stream:: "+ orderDto);
        productService.updateProductsById(orderDto);
    }

    @PutMapping
    public Stream<Product> updateProductByRequest(@RequestBody OrderItemDto[] orderItemDtoRequest) {
        return productService.updateProductByRequest(orderItemDtoRequest);
    }
}
