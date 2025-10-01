package com.devon.server.entities;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Rules {
    
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
     @JoinColumn(name = "lesson")
    private Grammar_Lessons lesson;

    public Rules() {}

    public String getRuleId() {
        return id;
    }     

    public Grammar_Lessons getLesson() {
        if(lesson == null) {
            return null;
        }
        return lesson;
    }
}
