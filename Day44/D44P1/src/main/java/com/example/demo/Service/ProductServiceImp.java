package com.example.demo.Service;

import com.example.demo.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private ArrayList<Product> pd = new ArrayList<>();

    public ProductServiceImp() {
        pd.add(new Product(101, "Television", 100000, 5, "Electronics"));
        pd.add(new Product(102, "Iphone", 150000, 6, "Electronics"));
        pd.add(new Product(103, "Keyboard", 10000, 15, "Electronics"));
        pd.add(new Product(201, "Shirt", 1000, 25, "Clothes"));
        pd.add(new Product(202, "Jeans", 1500, 24, "Clothes"));
        pd.add(new Product(203, "Socks", 100, 15, "Clothes"));
        pd.add(new Product(301, "Brush", 10, 45, "Utility"));
        pd.add(new Product(302, "Soap", 30, 45, "Utility"));
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(pd); // Return a copy to prevent external modification
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return pd.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return pd.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsGreaterThanPrice(int price) {
        return pd.stream()
                .filter(p -> p.getPrice() > price)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Product> getProductsLesserThanPrice(int price) {
        return pd.stream()
                .filter(p -> p.getPrice() < price)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Product> getProductsSortedByPriceAsc() {
        return pd.stream()
                .sorted(Comparator.comparingInt(Product::getPrice))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Product> getProductsSortedByPriceDesc() {
        return pd.stream()
                .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public int getProductCount() {
        return pd.size();
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return pd.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getMaxPriceProduct() {
        return pd.stream()
                .max(Comparator.comparingInt(Product::getPrice));
    }

    @Override
    public Optional<Product> getMinPriceProduct() {
        return pd.stream()
                .min(Comparator.comparingInt(Product::getPrice));
    }

    @Override
    public List<Product> getTopXExpensiveProducts(int x) {
        return pd.stream()
                .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                .limit(x)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getTopXCheapProducts(int x) {
        return pd.stream()
                .sorted(Comparator.comparingInt(Product::getPrice))
                .limit(x)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductByPrice(int price) {
        return pd.stream()
                .filter(p -> p.getPrice() == price)
                .findFirst();
    }

    @Override
    public double getAveragePrice() {
        return pd.stream()
                .mapToInt(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Product> getProductsByPage(int page, int size) {
        return pd.stream()
                .skip((long) (page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsInPriceRange(int min, int max) {
        return pd.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortProductsByName() {
        return pd.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    @Override
    public long countProductsByCategory(String category) {
        return pd.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .count();
    }

    @Override
    public List<String> getAllCategories() {
        return pd.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public String addProduct(Product product) {
        pd.add(product);
        return "Product added successfully";
    }

    @Override
    public List<Product> bulkAddProducts(List<Product> products) {
        pd.addAll(products);
        return products;
    }

    @Override
    public void updateProductPrice(int id, int newPrice) {
        getProductById(id).ifPresent(p -> p.setPrice(newPrice));
    }

    @Override
    public void updateProductCategory(int id, String newCategory) {
        getProductById(id).ifPresent(p -> p.setCategory(newCategory));
    }

    @Override
    public String updateProduct(Product product) {
        for (int i = 0; i < pd.size(); i++) {
            if (pd.get(i).getId() == product.getId()) {
                pd.set(i, product); // Replace the existing product with the updated one
                return "Product Updated Successfully";
            }
        }
        return "Product not found";
    }

    @Override
    public String deleteProduct(int id) {
        boolean removed = pd.removeIf(p -> p.getId() == id);
        return removed ? "Deleted successfully" : "No id found";
    }
}