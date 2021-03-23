package com.example.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyClass
public class ReviewKey {

    @PrimaryKeyColumn(name = "productId",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String productId;

    @PrimaryKeyColumn(name = "customerId",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private String customerId;
}
