package venu.in.coding.microservices.eureka.registery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerRegisteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerRegisteryApplication.class, args);
	}

}
