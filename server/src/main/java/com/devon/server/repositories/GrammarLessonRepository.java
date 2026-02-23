package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.Grammar_Lessons;

@Repository
public interface GrammarLessonRepository extends JpaRepository<Grammar_Lessons, Integer> {
    
}
