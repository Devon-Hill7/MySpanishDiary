package com.devon.server.services;

import org.springframework.stereotype.Service;

import com.devon.server.repositories.PromptRepository;
import com.devon.server.entities.Prompts;
import java.util.Random;
import java.util.List;

@Service
public class PromptService {
    
    private final PromptRepository promptRepository;
    private Random random = new Random();

    public PromptService(PromptRepository repo) {
        promptRepository = repo;
    }

    public Prompts getRandomPrompt() {
        List<Prompts> prompts = promptRepository.findAll();
        if (prompts.isEmpty()) {
            return null; 
        }
        return prompts.get(random.nextInt(prompts.size()));

    }
}
