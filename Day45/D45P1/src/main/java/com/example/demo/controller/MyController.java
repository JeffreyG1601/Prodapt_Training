// src/main/java/com/example/demo/controller/MyController.java
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/public")
    public String publicEndpoint() {
        return myService.getPublicMessage();
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return myService.getPrivateMessage();
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return myService.getAdminMessage();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return myService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return myService.getUserById(id);
    }
}