package com.devon.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.devon.server.dtos.LoginRequest;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "${frontend.url}")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    @PostMapping("/auth/login")
    public boolean login(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/auth/register")
    public boolean register(@RequestBody LoginRequest request) {
        return authenticationService.register(request);
    }
    
}
