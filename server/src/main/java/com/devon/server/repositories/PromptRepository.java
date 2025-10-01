package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.Prompts;


@Repository
public interface PromptRepository extends JpaRepository<Prompts, Long> {
 
}
