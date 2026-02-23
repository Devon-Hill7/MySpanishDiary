package com.devon.server.entities;

import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collections;

@Entity
public class Users implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // or roles later
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
