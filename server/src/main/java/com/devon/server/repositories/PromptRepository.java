package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.Prompt;


@Repository
public interface PromptRepository extends JpaRepository<Prompt, Long> {
 
}
