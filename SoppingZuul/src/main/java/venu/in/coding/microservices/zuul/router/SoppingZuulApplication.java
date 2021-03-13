package venu.in.coding.microservices.zuul.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class SoppingZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoppingZuulApplication.class, args);
	}

}
