package com.devon.server.services;

import com.devon.server.entities.GrammarLesson;
import com.devon.server.repositories.GrammarLessonRepository;

public class GrammarLessonService {
    
    private final GrammarLessonRepository grammarLessonRepository;

    public GrammarLessonService(GrammarLessonRepository repo) {
        grammarLessonRepository = repo;
    }

    public GrammarLesson getLesson(String id) {
        return grammarLessonRepository.findById(id).orElse(null);
    }
}
