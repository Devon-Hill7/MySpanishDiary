package com.devon.server.entities;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity
public class Rule {
    
    @Id
    private String ruleId;

    @ManyToOne
    private GrammarLesson lesson;

    public Rule() {}

    public String getRuleId() {
        return ruleId;
    }     

    public GrammarLesson getLesson() {
        return lesson;
    }
}
