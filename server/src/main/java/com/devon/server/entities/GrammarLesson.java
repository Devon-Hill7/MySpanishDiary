package com.devon.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GrammarLesson {
    
    @Id
    private String lessonId;

    private String title;

    private String videoURL;

    public GrammarLesson() {}

    public String getRuleId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoURL() {
        return videoURL;
    }

    
}
