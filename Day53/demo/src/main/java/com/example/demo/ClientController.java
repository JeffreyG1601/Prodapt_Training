package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call")
    public String callServiceB() {
        String url = "http://localhost:8080/api/message"; // Assuming both run on same port for demo
        return restTemplate.getForObject(url, String.class);
    }
}
