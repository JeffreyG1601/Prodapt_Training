package com.example.demo.service;

import com.example.demo.model.AuthRequest;
import com.example.demo.util.JWTUtil;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JWTUtil jwtUtil;

    public JwtService(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String login(AuthRequest request) {
        // Hardcoded credentials for demo
        if ("user".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            return jwtUtil.generateToken(request.getUsername());
        }
        return null;
    }
}
