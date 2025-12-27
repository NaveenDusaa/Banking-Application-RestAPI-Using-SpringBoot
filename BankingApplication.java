package com.example.banking;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BankingApplication.class);
		app.setDefaultProperties(
			Collections.singletonMap("server.port", "9091")
		);
		app.run(args);
		System.out.println("well");
	}
}
