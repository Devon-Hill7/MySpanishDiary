package com.devon.server.services;

import org.springframework.stereotype.Service;
import com.devon.server.repositories.EntryRepository;
import com.devon.server.entities.Entries;
import java.util.List;
import com.devon.server.dtos.EntryRequest;
import com.devon.server.repositories.PromptRepository;
import com.devon.server.repositories.UserRepository;
import com.devon.server.entities.Users;
import com.devon.server.entities.Prompts;
import java.util.Optional;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    private final PromptRepository promptRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final UserService userService;

    public EntryService(EntryRepository repo, PromptRepository promptRepo, UserRepository userRepo, JWTService jwtService, UserService userService) {
        entryRepository = repo;
        promptRepository = promptRepo;
        userRepository = userRepo;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public Entries getEntry(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    public List<Entries> getAllEntriesFromUser(String token) {
        String username = jwtService.extractUsername(token);
        int userId = userService.getUserByUsername(username).getId();
        return entryRepository.findAllEntriesByUserId(userId);

        
    }

    public boolean addEntryFromRequest(EntryRequest request) {
        Users user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Prompts prompt = promptRepository.findById(request.getPromptId()).orElseThrow(() -> new RuntimeException("Prompt not found"));
        Entries entry = new Entries(user, prompt, request.getDate(), request.getText());
        entryRepository.save(entry);
        if(entry.getId() != null) {
            return true;
        }
        return false;
    }

    public boolean updateEntryFromRequest(Long id, EntryRequest request) {
        Entries existingEntry = entryRepository.findById(id).orElseThrow(() -> new RuntimeException("Entry not found"));
        existingEntry.setText(request.getText());
        entryRepository.save(existingEntry);
        return true;
    }
}
