package com.careercrafter.service;

import com.careercrafter.dto.*;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    AuthResponse register(RegisterRequest request);
    void logout(String token);
}
