package venu.in.coding.microservices.order.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	int orderId;
	List<Product> products;
	double totalAmount;
	String streetName;
	String city;

}
