package com.devon.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import com.devon.server.dtos.LoginRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import com.devon.server.services.JWTService;
import com.devon.server.services.UserService;
import java.util.Map;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, JWTService jwtService, UserService userService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userService = userService;
    }
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Map<String, String> response = authenticationService.login(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid credentials"));
        }
}

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest request) {
        boolean isRegistered = authenticationService.register(request);
        if (isRegistered) {
            return ResponseEntity.ok(Map.of("message", "Registration successful"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Registration Failed"));
        }
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> extractUsername(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Map<String, String> response;
        try {
            response = Map.of("username", jwtService.extractUsername(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid token"));
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/getUserId")
    public ResponseEntity<?> getUserId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username;
        try {
            Map<String, String> usernameMap = Map.of("username", jwtService.extractUsername(token));
            username = usernameMap.get("username");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid token"));
        }

        return ResponseEntity.ok(Map.of("userId", userService.getUserByUsername(username).getId()));
    }
}
