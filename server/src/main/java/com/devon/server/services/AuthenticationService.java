package com.devon.server.services;

import org.springframework.stereotype.Service;
import com.devon.server.entities.Users;
import com.devon.server.repositories.UserRepository;
import com.devon.server.dtos.LoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.devon.server.services.JWTService;
import java.util.Map;

@Service
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public Map<String, String> login(LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );
            System.out.println("Authenticated: " + auth.isAuthenticated());
            return auth.isAuthenticated() ? Map.of("token", jwtService.generateToken(request.getUsername())) : null;
        } catch (Exception e) {
            System.out.println("Authentication failed for username: " + request.getUsername());
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean register(LoginRequest request) {
        String username = request.getUsername();

        Users user = userRepository.findByUsername(username);

       if (user != null) {
            return false;
        }

        String password = request.getPassword();
        String hashedPassword = passwordEncoder.encode(password);

        Users newUser = new Users(username, hashedPassword);
        userRepository.save(newUser);
        return true;

    }  
    
}