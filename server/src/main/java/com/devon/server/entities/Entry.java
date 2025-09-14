package com.devon.server.entities;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "prompt_id")
    private Prompt prompt;

    private String text;

    private LocalDate entryDate;

    public Entry() {};

    public Entry(User owner, Prompt chosenPrompt, LocalDate dateCreated) {
        this.user = owner;
        this.prompt = chosenPrompt;
        this.entryDate = dateCreated;
    };

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Prompt getPrompt() {
        return prompt;
    }
    public String getText() {
        return text;
    }
    public LocalDate getEntryDate() {
        return entryDate;
    }


}
