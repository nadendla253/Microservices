package venu.in.coding.microservices.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import venu.in.coding.microservices.order.pojo.Order;
import venu.in.coding.microservices.order.pojo.Product;
import venu.in.coding.microservices.order.service.OrderService;

@RestController
@RequestMapping("/order-service")
public class OrderController {

	@Autowired
	OrderService service;

	@GetMapping("/checkout/{category}")
	public List<Product> checkout(@PathVariable("category") String category) {
		List<Product> products = service.checkOut(category);
		return products;
	}

	@GetMapping("/place-order/{streetname}/{city}")
	public Order placeOrder(@PathVariable("streetname") String streetName, @PathVariable("city") String city) {
		Order order = service.placeOrder(streetName, city);
		return order;
	}

}
