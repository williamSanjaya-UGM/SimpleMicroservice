package com.example.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

    @PrimaryKey
    private ReviewKey reviewKey;

    @Column(value = "username")
    private String username;

    @Column(value = "comments")
    private String comments;

    @Column(value = "given_rating")
    private int given_rating;
}
