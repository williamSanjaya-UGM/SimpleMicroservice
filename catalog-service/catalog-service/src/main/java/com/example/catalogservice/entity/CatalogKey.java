package com.example.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyClass
public class CatalogKey implements Serializable {

    @PrimaryKeyColumn(name = "category",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String category;

    @PrimaryKeyColumn(name = "productId",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private String productId;
}
