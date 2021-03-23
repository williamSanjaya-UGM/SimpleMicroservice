package com.example.catalogservice.repository;

import com.example.catalogservice.entity.CatalogKey;
import com.example.catalogservice.entity.ProductCatalog;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCatalogRepository extends CassandraRepository<ProductCatalog, CatalogKey> {
    List<ProductCatalog> findByCatalogKeyCategory(final String category);

    Optional<ProductCatalog> findByCatalogKeyCategoryAndCatalogKeyProductId(final String category, final String productId);
}
