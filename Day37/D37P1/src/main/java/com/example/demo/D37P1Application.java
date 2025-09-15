package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class D37P1Application {

	public static void main(String[] args) {
		SpringApplication.run(D37P1Application.class, args);
		System.out.println("<h1> This is a test</h1>");
	}

}
