package com.devon.server.entities;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Entries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "prompt_id")
    private Prompts prompt;

    private String text;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    public Entries() {};

    public Entries(Users owner, Prompts chosenPrompt, LocalDate dateCreated, String entryText) {
        this.user = owner;
        this.prompt = chosenPrompt;
        this.entryDate = dateCreated;
        this.text = entryText;
    };

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }
    public Users getUser() {
        return user;
    }
    public Prompts getPrompt() {
        return prompt;
    }
    public String getText() {
        return text;
    }
    public LocalDate getEntryDate() {
        return entryDate;
    }


}
