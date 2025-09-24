package com.example.demo.service;


import java.util.Optional;

import com.example.demo.model.User;

public interface UserService {
    Optional<User> getUserById(Long id);
}