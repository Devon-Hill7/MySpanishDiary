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


@Service
public class EntryService {
    private final EntryRepository entryRepository;
    private final PromptRepository promptRepository;
    private final UserRepository userRepository;

    public EntryService(EntryRepository repo, PromptRepository promptRepo, UserRepository userRepo) {
        entryRepository = repo;
        promptRepository = promptRepo;
        userRepository = userRepo;
    }

    public Entries getEntry(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    public List<Entries> getAllEntries() {
        return entryRepository.findAll();
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

}
