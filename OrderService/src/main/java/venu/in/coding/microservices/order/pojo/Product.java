package venu.in.coding.microservices.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private int product_Id;
	private String product_Name;
	private String product_Category;
	private double product_Price;

}
