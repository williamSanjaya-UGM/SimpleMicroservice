package com.example.gatewayservice.controller;

import com.example.gatewayservice.controller.dto.ProductCatalogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{category}")
    public ResponseEntity<ProductCatalogDto[]> getAllCatalogs(@PathVariable String category) {
        return restTemplate.getForEntity("http://catalog-service/catalog/"+category,ProductCatalogDto[].class);
    }
}
