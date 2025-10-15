package com.example.demo.service;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> getAll() { return repo.findAll(); }
    public Optional<Product> getById(Long id) { return repo.findById(id); }
    public Product save(Product p) { return repo.save(p); }

    public Product update(Long id, Product p) {
        return repo.findById(id).map(existing -> {
            existing.setName(p.getName());
            existing.setCategory(p.getCategory());
            existing.setPrice(p.getPrice());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public void delete(Long id) { repo.deleteById(id); }
}
