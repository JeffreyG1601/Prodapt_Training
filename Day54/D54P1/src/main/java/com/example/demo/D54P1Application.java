package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class D54P1Application {
    public static void main(String[] args) {
        SpringApplication.run(D54P1Application.class, args);
        System.out.println("test123/........");
    }
}
