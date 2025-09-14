package com.devon.server.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.*;
import com.devon.server.entities.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }
    

    @GetMapping("/entries")
    public List<Entry> getEntries() {
       return entryService.getAllEntries();
    }

    @GetMapping("/entries/{id}")
    public Entry getEntry(@PathVariable Long id) {
         return entryService.getEntry(id);
    }

    @PostMapping("/entries")
    public void addEntry(@RequestBody Entry entry) {
        entryService.addEntry(entry);
    }



}
