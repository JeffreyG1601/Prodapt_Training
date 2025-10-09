package com.careercrafter.service;

import com.careercrafter.dto.AuthRequest;
import com.careercrafter.dto.AuthResponse;
import com.careercrafter.dto.RegisterRequest;
import com.careercrafter.model.User;
import com.careercrafter.repository.UserRepository;
import com.careercrafter.security.JwtUtils;
import com.careercrafter.security.TokenBlacklist;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final TokenBlacklist tokenBlacklist;

    public AuthServiceImpl(AuthenticationManager authManager, UserRepository userRepo,
                           PasswordEncoder passwordEncoder, JwtUtils jwtUtils,
                           TokenBlacklist tokenBlacklist) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        Set<String> roles = principal.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toSet());

        String token = jwtUtils.generateToken(principal.getUsername(), roles);
        return new AuthResponse(token, "Bearer");
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        Set<String> roleStrings = request.getRoles() != null && !request.getRoles().isEmpty()
                ? request.getRoles()
                : Set.of("ROLE_JOB_SEEKER");

        User u = new User(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                roleStrings
        );

        userRepo.save(u);

        String token = jwtUtils.generateToken(u.getEmail(), roleStrings);
        return new AuthResponse(token, "Bearer");
    }

    @Override
    public void logout(String token) {
        tokenBlacklist.blacklist(token);
    }
}
