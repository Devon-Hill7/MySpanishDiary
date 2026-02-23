package com.devon.server.services;

import org.springframework.stereotype.Service;
import com.devon.server.entities.Users;
import com.devon.server.repositories.UserRepository;
import com.devon.server.dtos.LoginRequest;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public boolean login(LoginRequest request) {
        try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        return true;
    } catch (Exception e) {
        System.out.println("Exception type: " + e.getClass().getName());
        // Print the exception message
        System.out.println("Exception message: " + e.getMessage());
        return false;
    }
    }

    public boolean register(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        Users user = userRepository.findByUsername(username).orElse(null);

       if (user != null) {
            return false;
        }

        String hashedPassword = passwordEncoder.encode(password);

        Users newUser = new Users(username, hashedPassword);
        userRepository.save(newUser);
        return true;
        

    }  
    
}