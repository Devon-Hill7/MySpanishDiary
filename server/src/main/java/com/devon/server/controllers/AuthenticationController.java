package com.devon.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import com.devon.server.dtos.LoginRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public Map<String, String> login(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/auth/register")
    public boolean register(@RequestBody LoginRequest request) {
        return authenticationService.register(request);
    }

    @GetMapping("/auth/me")
    public Map<String, String> extractUsername(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return Map.of("username", jwtService.extractUsername(token));
    }

    @GetMapping("/auth/getUserId")
    public Map<String, Integer> getUserId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Map<String, String> usernameMap = Map.of("username", jwtService.extractUsername(token));
        String username = usernameMap.get("username");
        return Map.of("userId", userService.getUserByUsername(username).getId());
    }
    
}
