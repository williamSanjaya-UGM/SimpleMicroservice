package com.example.gatewayservice.service;

import com.example.gatewayservice.controller.dto.ProductReviewDto;
import com.example.gatewayservice.controller.dto.UserDto;
import com.example.gatewayservice.service.PayloadData.PayloadReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<ProductReviewDto> addReview(PayloadReview payloadReview) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userObject = restTemplate.getForObject("http://customer-service/" + email, UserDto.class);
        String username = userObject.getUsername();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String,String> reviewKeyMapping= new HashMap<>();
        reviewKeyMapping.put("productId",payloadReview.getProductId());
        reviewKeyMapping.put("customerId",email);

        Map<String,Object> reviewProductMapping =new HashMap<>();
        reviewProductMapping.put("reviewKey",reviewKeyMapping);
        reviewProductMapping.put("username",username);
        reviewProductMapping.put("comments",payloadReview.getComments());
        reviewProductMapping.put("given_rating",payloadReview.getGiven_rating());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(reviewProductMapping, headers);
        return restTemplate.postForEntity("http://catalog-service/review/",request,ProductReviewDto.class);
    }

    public ResponseEntity<?> deleteReview(String productId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        restTemplate.delete("http://catalog-service/review/"+productId+"/delete/"+email);
        return ResponseEntity.ok().build();
    }
}
