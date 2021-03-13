package venu.in.coding.microservices.product.service;

import java.util.List;

import venu.in.coding.microservices.product.pojo.Product;

public interface ProductService {

	public List<Product> findAll();

	public List<Product> findByCategory(String category);

	public Product findById(int id);

}
