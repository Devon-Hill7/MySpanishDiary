package com.devon.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.PromptService;
import org.springframework.web.bind.annotation.GetMapping;
import com.devon.server.entities.Prompts;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class PromptController {

    private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/prompt")
    public Prompts getPrompt() {
        return promptService.getRandomPrompt();
    }
    
}