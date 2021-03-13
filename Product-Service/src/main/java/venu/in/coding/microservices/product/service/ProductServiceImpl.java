package venu.in.coding.microservices.product.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import venu.in.coding.microservices.product.pojo.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findAll() {

		return ListOfAllProducts();
	}

	@Override
	public List<Product> findByCategory(String category) {

		return ListOfAllProducts().stream().filter((product) -> product.getProduct_Category().equals(category))
				.collect(Collectors.toList());
	}

	@Override
	public Product findById(int id) {

		Product product = new Product();
		for (Product products : ListOfAllProducts()) {
			if (products.getProduct_Id() == id) {
				product = products;
			}
		}
		return product;

	}

	private List<Product> ListOfAllProducts() {
		return Arrays.asList(new Product(1, "Vivo-1726", "Phone", 14359.99),
				new Product(2, "LG-778", "Phone", 14353.00), new Product(3, "LED-24Inch", "TV", 44357.79),
				new Product(4, "Samsung-123", "TV", 53427.03), new Product(5, "Iphone-6", "Phone", 67399.99),
				new Product(6, "iphone-10", "Phone", 88762.29), new Product(7, "LG-220", "TV", 32000.15),
				new Product(8, "Onida", "TV", 21098.63));

	}

}
