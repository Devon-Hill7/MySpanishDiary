package com.devon.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Grammar_Lessons {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int lessonId;

    private String title;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String incorrectSentence;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> suggestions;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int errorStartPos;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int errorEndPos;

    @Column(name = "video_title")
    private String videoTitle;

    public Grammar_Lessons() {}

    public int getRuleId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getIncorrectSentence() {
        return incorrectSentence;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public int getErrorStartPos() {
        return errorStartPos;
    }

    public int getErrorEndPos() {
        return errorEndPos;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIncorrectSentence(String incorrectSentence) {
        this.incorrectSentence = incorrectSentence;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public void setErrorStartPos(int errorStartPos) {
        this.errorStartPos = errorStartPos;
    }

    public void setErrorEndPos(int errorEndPos) {
        this.errorEndPos = errorEndPos;
    }

    
}
