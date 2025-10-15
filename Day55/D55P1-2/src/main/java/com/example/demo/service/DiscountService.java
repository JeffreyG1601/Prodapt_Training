package com.example.demo.service;
import com.example.demo.dto.ProductDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DiscountService {

    private final RestTemplate restTemplate;
    // Use the product service application name (spring.application.name) registered in Eureka
    private static final String PRODUCT_SERVICE = "http://D55P1-1";

    public DiscountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductDTO> getAllProductsWithDiscount() {
        ResponseEntity<List<ProductDTO>> resp = restTemplate.exchange(
                PRODUCT_SERVICE + "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDTO>>() {}
        );
        List<ProductDTO> list = resp.getBody();
        if (list != null) list.forEach(p -> p.setPrice(applyDiscount(p.getPrice())));
        return list;
    }

    public ProductDTO getByIdWithDiscount(Long id) {
        ProductDTO p = restTemplate.getForObject(PRODUCT_SERVICE + "/products/" + id, ProductDTO.class);
        if (p != null) p.setPrice(applyDiscount(p.getPrice()));
        return p;
    }

    private Double applyDiscount(Double price) {
        if (price == null) return null;
        double discounted = price * 0.95; // 5% discount
        // round to 2 decimals
        return Math.round(discounted * 100.0) / 100.0;
    }
}
