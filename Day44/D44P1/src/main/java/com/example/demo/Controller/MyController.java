package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService; // Import the service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

    private final ProductService productService; // Inject the service

    @Autowired
    public MyController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/productdetails")
    List<Product> displayall() {
        return productService.getAllProducts();
    }

    @GetMapping("/productdetails/{id}")
    Product displayproduct(@PathVariable int id) {
        return productService.getProductById(id).orElse(null);
    }

    @GetMapping("/productdetails/category/{category}")
    public List<Product> displaycatproduct(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/productdetails/price/greater/{price}")
    public List<Product> displaygreatprice(@PathVariable int price) {
        return productService.getProductsGreaterThanPrice(price);
    }

    @GetMapping("/productdetails/price/lesser/{price}")
    public List<Product> displaylessprice(@PathVariable int price) {
        return productService.getProductsLesserThanPrice(price);
    }

    @GetMapping("/productdetails/price/asc")
    public List<Product> displayascprice() {
        return productService.getProductsSortedByPriceAsc();
    }

    @GetMapping("/productdetails/price/desc")
    public List<Product> displaydescprice() {
        return productService.getProductsSortedByPriceDesc();
    }

    @GetMapping("/productdetails/count")
    int displaycount() {
        return productService.getProductCount();
    }

    @GetMapping("/productdetails/search/{name}")
    List<Product> displayproductByName(@PathVariable String name) {
        return productService.searchProductsByName(name);
    }

    @GetMapping("/productdetails/max-price")
    public Product getMaxPriceProduct() {
        return productService.getMaxPriceProduct().orElse(null);
    }

    @GetMapping("/productdetails/min-price")
    public Product getMinPriceProduct() {
        return productService.getMinPriceProduct().orElse(null);
    }

    @GetMapping("/productdetails/max-price/{x}")
    public List<Product> getTopXExpensiveProducts(@PathVariable int x) {
        return productService.getTopXExpensiveProducts(x);
    }

    @GetMapping("/productdetails/min-price/{x}")
    public List<Product> getTopXCheapProducts(@PathVariable int x) {
        return productService.getTopXCheapProducts(x);
    }

    @GetMapping("/productdetails/price/{price}")
    Product displayproductbyprice(@PathVariable int price) {
        return productService.getProductByPrice(price).orElse(null);
    }

    @GetMapping("/productdetails/avg-price")
    public double getAveragePrice() {
        return productService.getAveragePrice();
    }

    @GetMapping("/page/{page}/size/{size}")
    public List<Product> getProductsByPage(@PathVariable int page, @PathVariable int size) {
        return productService.getProductsByPage(page, size);
    }

    @GetMapping("/productdetails/price/{min}/{max}")
    public List<Product> getProductsInPriceRange(@PathVariable int min, @PathVariable int max) {
        return productService.getProductsInPriceRange(min, max);
    }

    @GetMapping("/productdetails/sort/name")
    public List<Product> sortByName() {
        return productService.sortProductsByName();
    }

    @GetMapping("/category/count/{category}")
    public long countByCategory(@PathVariable String category) {
        return productService.countProductsByCategory(category);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    @PostMapping("/productdetails")
    String adddetail(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/productdetails/bulk-add")
    public List<Product> bulkAddProducts(@RequestBody List<Product> products) {
        return productService.bulkAddProducts(products);
    }

    @PatchMapping("/productdetails/{id}/price/{newprice}")
    void updateprice(@PathVariable int id, @PathVariable("newprice") int nprice) {
        productService.updateProductPrice(id, nprice);
    }

    @PatchMapping("/productdetails/{id}/category/{newcategory}")
    void updatecat(@PathVariable int id, @PathVariable String newcategory) {
        productService.updateProductCategory(id, newcategory);
    }

    @PutMapping("/productdetails")
    String updateprod(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/productdetails/{id}")
    String deletedetails(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}