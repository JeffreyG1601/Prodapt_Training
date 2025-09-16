package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.demo.Model.Product;


@RestController
public class MyController {
	ArrayList<Product> pd = new ArrayList<Product>();
	public MyController(){
		pd.add(new Product(101, "Television",100000,5, "Electronics"));
		pd.add(new Product(102, "Iphone",150000,6, "Electronics"));
		pd.add(new Product(103, "Keyboard",10000,15, "Electronics"));
		pd.add(new Product(201, "Shirt",1000,25, "Clothes"));
		pd.add(new Product(202, "Jeans",1500,24, "Clothes"));
		pd.add(new Product(203, "Socks",100,15, "Clothes"));
		pd.add(new Product(301, "Brush",10,45, "Utility"));
		pd.add(new Product(302, "Soap",30,45, "Utility"));
	}
	@GetMapping("/productdetails")
	ArrayList<Product> displayall() {
		return pd;
	}
	@GetMapping("/productdetails/{id}")
	Product displayproduct(@PathVariable int id) {
		for (Product p : pd) {
			if(p.getId()==id) {
				return p;
			}
		}
		return null;
	}
	@GetMapping("/productdetails/category/{category}")
	public ArrayList<Product> displaycatproduct(@PathVariable String category) {
	    return (ArrayList<Product>) pd.stream()
	             .filter(p -> p.getCategory().equalsIgnoreCase(category))
	             .collect(Collectors.toList());
	}
	@GetMapping("/productdetails/price/greater/{price}")
	public ArrayList<Product> displaygreatprice(@PathVariable int price) {
	    return pd.stream()
	             .filter(p -> p.getPrice() > price)
	             .collect(Collectors.toCollection(ArrayList::new));
	}
	@GetMapping("/productdetails/price/lesser/{price}")
	public ArrayList<Product> displaylessprice(@PathVariable int price) {
	    return pd.stream()
	             .filter(p -> p.getPrice() < price)
	             .collect(Collectors.toCollection(ArrayList::new));
	}
	@GetMapping("/productdetails/price/asc")
	public ArrayList<Product> displayascprice() {
	    return pd.stream()
	             .sorted(Comparator.comparingInt(Product::getPrice))
	             .collect(Collectors.toCollection(ArrayList::new));
	}
	@GetMapping("/productdetails/price/desc")
	public ArrayList<Product> displaydescprice() {
	    return pd.stream()
	             .sorted(Comparator.comparingInt(Product::getPrice).reversed())
	             .collect(Collectors.toCollection(ArrayList::new));
	}
	@GetMapping("/productdetails/count")
	int displaycount() {
		int count =0;
		for (Product p:pd) {
			count++;
		}
		return count;
	}
	@GetMapping("/productdetails/search/{name}")
	ArrayList<Product> displayproduct(@PathVariable String name) {
		return (ArrayList<Product>) pd.stream()
	             .filter(p -> p.getName().equalsIgnoreCase(name))
	             .collect(Collectors.toList());
	 }
	@GetMapping("/productdetails/max-price")
	public Product getMaxPriceProduct() {
	    return pd.stream()
	             .max(Comparator.comparingInt(Product::getPrice))
	             .orElse(null); // return null if pd is empty
	}
	@GetMapping("/productdetails/min-price")
	public Product getMinPriceProduct() {
	    return pd.stream()
	             .min(Comparator.comparingInt(Product::getPrice))
	             .orElse(null); // return null if pd is empty
	}
	@GetMapping("/productdetails/max-price/{x}")
	public List<Product> getTopXExpensiveProducts(@PathVariable int x) {
	    return pd.stream()
	             .sorted(Comparator.comparingInt(Product::getPrice).reversed()) // sort descending
	             .limit(x)  // pick only top x
	             .collect(Collectors.toList());
	}
	@GetMapping("/productdetails/min-price/{x}")
	public List<Product> getTopXCheapProducts(@PathVariable int x) {
	    return pd.stream()
	             .sorted(Comparator.comparingInt(Product::getPrice)) // sort descending
	             .limit(x)  // pick only top x
	             .collect(Collectors.toList());
	}
	@GetMapping("/productdetails/price/{price}")
	Product displayproductbyprice(@PathVariable int price) {
		for (Product p : pd) {
			if(p.getPrice()==price) {
				return p;
			}
		}
		return null;
	}
	@GetMapping("/productdetails/avg-price")
	public double getAveragePrice() {
	    return pd.stream()
	             .mapToInt(Product::getPrice)
	             .average()
	             .orElse(0.0); // return 0 if list is empty
	}

	@GetMapping("/page/{page}/size/{size}")
	public List<Product> getProductsByPage(@PathVariable int page, @PathVariable int size) {
	    return pd.stream()
	             .skip((long) (page - 1) * size) // skip items of previous pages
	             .limit(size) // take only 'size' items
	             .collect(Collectors.toList());
	}
	@GetMapping("/productdetails/price/{min}/{max}")
	public List<Product> getProductsInPriceRange(@PathVariable int min, @PathVariable int max) {
	    return pd.stream()
	             .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
	             .collect(Collectors.toList());
	}
	@GetMapping("/productdetails/sort/name")
	public List<Product> sortByName() {
	    return pd.stream()
	             .sorted(Comparator.comparing(Product::getName)) 
	             .collect(Collectors.toList());
	}

	@GetMapping("/category/count/{category}")
	public long countByCategory(@PathVariable String category) {
	    return pd.stream()
	             .filter(p -> p.getCategory().equalsIgnoreCase(category))
	             .count();
	}

	@GetMapping("/categories")
	public List<String> getAllCategories() {
	    return pd.stream()
	             .map(Product::getCategory)
	             .distinct()
	             .collect(Collectors.toList());
	}
	@PostMapping("/productdetails")
	String adddetail(@RequestBody Product product) {
		pd.add(product);
		return "Product added successfully";
	}
	@PostMapping("/productdetails/bulk-add")
	public List<Product> bulkAddProducts(@RequestBody List<Product> products) {
	    pd.addAll(products);  // pd is your product list
	    return products;      // return the added products as confirmation
	}
	@PatchMapping("/productdetails/{id}/price/{newprice}")
	void updateprice(@PathVariable int id,@PathVariable("newprice") int nprice) {
		for (Product p : pd) {
			if(p.getId()==id) {
				p.setPrice(nprice);
			}
		}
	}
	@PatchMapping("/productdetails/{id}/category/{newcategory}")
	void updatecat(@PathVariable int id, @PathVariable String newcategory) {
		for (Product p:pd) {
			if(p.getId()==id) {
				p.setCategory(newcategory);
			}
		}
	}
	@PutMapping("/productdetails")
	String updateprod(@RequestBody Product product) {
		for (Product p : pd) {
			if(p.getId()==product.getId()) {
				p.setName(product.getName());
				p.setPrice(product.getPrice());
				p.setQuantity(product.getQuantity());
				p.setCategory(product.getCategory());
				return "Product Updated Successfully";
			}
		}
		return "Product not found";
	}
	@DeleteMapping("/productdetails/{id}")
	String deletedetails(@PathVariable int id) {
		for (Product p:pd) {
			if(p.getId()==id) {
				pd.remove(p);
				return "Deleted successfully";
			}
		}
		return "No id found";
	}
}
