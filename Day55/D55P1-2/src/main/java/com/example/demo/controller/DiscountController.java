package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class DiscountController {

    private final DiscountService service;
    public DiscountController(DiscountService service) { this.service = service; }

    @GetMapping
    public List<ProductDTO> getAll() { return service.getAllProductsWithDiscount(); }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO p = service.getByIdWithDiscount(id);
        return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    // Optionally you can add POST/PUT/DELETE proxy methods to call D55P1-1 if you want the client to mirror all operations.
}
