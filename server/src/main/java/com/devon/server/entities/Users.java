package com.devon.server.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;

    private String password;

    @Column(name = "streak_count")
    private Long streak;

    @Column(name = "last_entry_date")
    private LocalDate lastEntryDate;

    public Users() {}

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.streak = 0L;
        this.lastEntryDate = null;
    }

    public void setStreak(Long streak) {
        this.streak = streak;
    }

    public void setLastEntryDate(LocalDate lastEntryDate) {
        this.lastEntryDate = lastEntryDate;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getStreak() {
        return streak;
    }

    public LocalDate getLastEntryDate() {
        return lastEntryDate;
    }
}
