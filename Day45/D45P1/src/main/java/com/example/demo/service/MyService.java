// src/main/java/com/example/demo/service/MyService.java
package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MyService {

    private final List<User> users = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public MyService() {
        // Adding some initial data
        users.add(new User(String.valueOf(idCounter.incrementAndGet()), "Alice"));
        users.add(new User(String.valueOf(idCounter.incrementAndGet()), "Bob"));
        users.add(new User(String.valueOf(idCounter.incrementAndGet()), "Charlie"));
    }

    public String getPublicMessage() {
        return "This is a public message!";
    }

    public String getPrivateMessage() {
        return "This is a private message, only for authenticated users!";
    }

    public String getAdminMessage() {
        return "This is an admin-only message!";
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public Optional<User> getUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}