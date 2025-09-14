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
public class Controller {

    private final UserService userService;
    private final PromptService promptService;
    private final EntryService entryService;

    public Controller(UserService userService, PromptService promptService, EntryService entryService) {
        this.userService = userService;
        this.promptService = promptService;
        this.entryService = entryService;
    }
    
    @GetMapping("/prompt")
    public Prompt addUser() {
       return promptService.getRandomPrompt();
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
