package venu.in.coding.microservices.cart.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import venu.in.coding.microservices.cart.pojo.Product;

@RestController
@RequestMapping("/cart-service")
public class CartController {

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	private static final String BASE_URI = "http://192.168.0.4:8086//product-service";
	private static List<Product> productList = new ArrayList<Product>();

	@GetMapping("/add-many-to-cart/{category}")
	public List<Product> addManyToCart(@PathVariable("category") String category) {
		String url = BASE_URI + "/products-by-category/" + category;
		
		ServiceInstance instance = loadBalancerClient.choose("PRODUCT-SERVICE");
		System.out.println("Port Number to pick product service Is: "+instance.getPort());

		List<LinkedHashMap<String, Object>> response = template.getForObject(url, List.class);
		List<Product> tempList = new ArrayList<Product>();
		for (LinkedHashMap<String, Object> productMap : response) {
			int id = (int) productMap.get("product_Id");
			String name = (String) productMap.get("product_Name");
			String product_category = (String) productMap.get("product_Category");
			double price = (double) productMap.get("product_Price");
			Product product = new Product(id, name, product_category, price);
			tempList.add(product);

		}

		productList = tempList.stream().filter((products) -> products.getProduct_Category().equals(category))
				.collect(Collectors.toList());

		return productList;
	}
	
	@GetMapping("/view-cart")
	public List<Product> viewCart(){
		return productList;
	}

	@GetMapping("/add-one-to-cart/{id}")
	public Product addOneToCart(@PathVariable("id") int product_Id) {
		productList.clear();
		String url = BASE_URI+"/product-by-id/"+product_Id;
		Product product = template.getForObject(url, Product.class);
		productList.add(product);
		return product;
	}
}
