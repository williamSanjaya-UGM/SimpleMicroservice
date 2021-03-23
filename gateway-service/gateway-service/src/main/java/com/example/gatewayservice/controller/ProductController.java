package com.example.gatewayservice.controller;

import com.example.gatewayservice.controller.dto.ProductCatalogDto;
import com.example.gatewayservice.controller.dto.ProductDto;
import com.example.gatewayservice.controller.dto.ProductReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getAllProducts(@PathVariable String productId) {
        return restTemplate.getForEntity("http://redis-cache-service/products/"+productId,ProductDto.class);
    }

    @GetMapping("/review/{productId}")
    public ResponseEntity<ProductReviewDto[]> getProductReview(@PathVariable String productId) {
        return restTemplate.getForEntity("http://catalog-service/review/"+productId, ProductReviewDto[].class);
    }
}
