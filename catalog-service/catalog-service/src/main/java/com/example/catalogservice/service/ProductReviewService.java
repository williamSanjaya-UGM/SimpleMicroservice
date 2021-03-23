package com.example.catalogservice.service;

import com.example.catalogservice.entity.ProductReview;
import com.example.catalogservice.exception.ResourceNotFoundException;
import com.example.catalogservice.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public ProductReview addReviewProduct(ProductReview productReview) {
        return productReviewRepository.save(productReview);
    }

    public List<ProductReview> findReviewByProductId(String productId){
        return productReviewRepository.findByReviewKeyProductId(productId);
    }

    public ProductReview updateReviewProduct(ProductReview productReviewRequest) {
        return productReviewRepository.save(productReviewRequest);
    }

    public ResponseEntity<?> deleteReviewProduct(String productId, String customerId) {
        return productReviewRepository.findByReviewKeyProductIdAndReviewKeyCustomerId(productId, customerId)
                .map(productReview -> {
                    productReviewRepository.delete(productReview);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("productId not found"));
    }
}
