package com.devon.server.services;

import org.springframework.stereotype.Service;
import com.devon.server.entities.Users;
import com.devon.server.repositories.UserRepository;
import com.devon.server.dtos.LoginRequest;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        Optional<Users> user = userRepository.findByUsername(username);

        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return true;

        }

        return false;
    }

    public boolean register(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        Optional<Users> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return false;
        }

        String hashedPassword = passwordEncoder.encode(password);

        Users newUser = new Users(username, hashedPassword);
        userRepository.save(newUser);
        return true;


    }
    
}