package com.example.catalogservice.repository;

import com.example.catalogservice.entity.ProductReview;
import com.example.catalogservice.entity.ReviewKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends CassandraRepository<ProductReview, ReviewKey> {
    List<ProductReview> findByReviewKeyProductId(final String productId);

    Optional<ProductReview> findByReviewKeyProductIdAndReviewKeyCustomerId(final String productId, final String customerId);
}
