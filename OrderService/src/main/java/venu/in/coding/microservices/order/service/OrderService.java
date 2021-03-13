package venu.in.coding.microservices.order.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import venu.in.coding.microservices.order.pojo.Order;
import venu.in.coding.microservices.order.pojo.Product;

@Service
public class OrderService {

	@Autowired
	private RestTemplate template;

	private static final String BASE_URI = "http://localhost:8082/cart-service";
	private static List<Product> productList = new ArrayList<Product>();
	int orderId;

	@HystrixCommand(fallbackMethod = "fallbackCheckOut")
	public List<Product> checkOut(String category) {
		String uri = BASE_URI + "/add-many-to-cart/" + category;

		List<LinkedHashMap<String, Object>> response = template.getForObject(uri, List.class);
		for (LinkedHashMap<String, Object> productMap : response) {
			int id = (int) productMap.get("product_Id");
			String name = (String) productMap.get("product_Name");
			String product_category = (String) productMap.get("product_Category");
			double price = (double) productMap.get("product_Price");
			Product product = new Product(id, name, product_category, price);
			productList.add(product);
		}
		return productList;
	}
	public List<Product> fallbackCheckOut(String category){
		return new ArrayList<>();
	}

	public Order placeOrder(String streetName, String city) {
		double total = 0;
		for (Product product : productList) {
			total += product.getProduct_Price();
		}
		Order order = new Order(++orderId, productList, total, streetName, city);
		return order;
	}
}
