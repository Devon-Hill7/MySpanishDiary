package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.GrammarLesson;

@Repository
public interface GrammarLessonRepository extends JpaRepository<GrammarLesson, String> {
    
}
