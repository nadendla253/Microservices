package venu.in.coding.microservices.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import venu.in.coding.microservices.product.pojo.Product;
import venu.in.coding.microservices.product.service.ProductService;

@RestController
@RequestMapping("/product-service")
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return service.findAll();
	}

	@GetMapping("/products-by-category/{category}")
	public List<Product> findByCategory(@PathVariable("category") String category) {
		return service.findByCategory(category);

	}

	@GetMapping("/product-by-id/{id}")
	public Product findById(@PathVariable("id") int id) {
		return service.findById(id);
	}

}
