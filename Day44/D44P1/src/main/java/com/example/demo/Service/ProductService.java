package com.example.demo.Service;

import com.example.demo.Model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsGreaterThanPrice(int price);
    List<Product> getProductsLesserThanPrice(int price);
    List<Product> getProductsSortedByPriceAsc();
    List<Product> getProductsSortedByPriceDesc();
    int getProductCount();
    List<Product> searchProductsByName(String name);
    Optional<Product> getMaxPriceProduct();
    Optional<Product> getMinPriceProduct();
    List<Product> getTopXExpensiveProducts(int x);
    List<Product> getTopXCheapProducts(int x);
    Optional<Product> getProductByPrice(int price);
    double getAveragePrice();
    List<Product> getProductsByPage(int page, int size);
    List<Product> getProductsInPriceRange(int min, int max);
    List<Product> sortProductsByName();
    long countProductsByCategory(String category);
    List<String> getAllCategories();
    String addProduct(Product product);
    List<Product> bulkAddProducts(List<Product> products);
    void updateProductPrice(int id, int newPrice);
    void updateProductCategory(int id, String newCategory);
    String updateProduct(Product product);
    String deleteProduct(int id);
}