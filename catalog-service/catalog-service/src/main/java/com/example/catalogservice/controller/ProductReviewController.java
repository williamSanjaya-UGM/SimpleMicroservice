package com.example.catalogservice.controller;

import com.example.catalogservice.entity.ProductReview;
import com.example.catalogservice.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping
    public ProductReview addReviewProduct(@RequestBody ProductReview productReview) {
        return productReviewService.addReviewProduct(productReview);
    }

    @GetMapping("/{productId}")
    public List<ProductReview> findReviewByProductId(@PathVariable String productId){
        return productReviewService.findReviewByProductId(productId);
    }

    @PutMapping
    public ProductReview updateReviewProduct(@RequestBody ProductReview productReviewRequest) {
        return productReviewService.updateReviewProduct(productReviewRequest);
    }

    @DeleteMapping("/{productId}/delete/{customerId}")
    public ResponseEntity<?> deleteReviewProduct(@PathVariable String productId, @PathVariable String customerId) {
        return productReviewService.deleteReviewProduct(productId, customerId);
    }
}
