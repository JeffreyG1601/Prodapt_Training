// src/main/java/com/example/demo/config/SecurityConfig.java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enables Spring Security's method-level security annotations like @PreAuthorize
public class SecurityConfig {

    // 1. PasswordEncoder: Used to encode passwords for secure storage.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. UserDetailsService: Configures in-memory users for demonstration.
    // In a real application, this would interact with a database.
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Create a regular user with "USER" role
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        // Create an admin user with "ADMIN" and "USER" roles
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("adminpass"))
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // 3. SecurityFilterChain: This is the core of Spring Security configuration.
    // It defines how HTTP requests are secured.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for simplicity in this example. In production, keep it enabled
                // or ensure proper CSRF token handling for non-GET requests.
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // Allow unauthenticated access to the public endpoint
                        .requestMatchers("/api/public").permitAll()
                        // Require "USER" role for the private endpoint
                        .requestMatchers("/api/private").hasRole("USER")
                        // Require "ADMIN" role for the admin endpoint
                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        // Allow anyone (authenticated or not) to view all users
                        .requestMatchers("/api/users").permitAll()
                        // Allow anyone (authenticated or not) to view a user by ID
                        .requestMatchers("/api/users/{id}").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // Configure basic HTTP authentication for demonstration
                .httpBasic(withDefaults()); // Uses a popup for username/password in the browser

        return http.build();
    }
}