package com.devon.server.services;

import org.springframework.stereotype.Service;
import com.devon.server.repositories.EntryRepository;
import com.devon.server.entities.Entry;
import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository repo) {
        entryRepository = repo;
    }

    public Entry getEntry(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    public void addEntry(Entry entry) {
        entryRepository.save(entry);
    }

}
