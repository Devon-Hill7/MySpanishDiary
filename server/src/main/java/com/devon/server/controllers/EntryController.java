package com.devon.server.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.*;
import org.springframework.web.bind.annotation.PathVariable;
import com.devon.server.dtos.EntryRequest;
import com.devon.server.entities.Entries;

@CrossOrigin(origins = "${frontend.url}")
@RestController
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }
    

    @GetMapping("/entries")
    public List<Entries> getEntries(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return entryService.getAllEntriesFromUser(token);
    }

    @GetMapping("/entries/{id}")
    public Entries getEntry(@PathVariable Long id) {
         return entryService.getEntry(id);
    }

    @PostMapping("/entries")
    public boolean addEntry(@RequestBody EntryRequest request) {
        return entryService.addEntryFromRequest(request);
    }

    @PutMapping("/entries/{id}")
    public boolean updateEntry(@PathVariable Long id, @RequestBody EntryRequest request) {
        return entryService.updateEntryFromRequest(id, request);
    }

    



}
