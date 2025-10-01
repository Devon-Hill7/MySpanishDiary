package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devon.server.entities.Rules;

@Repository
public interface RuleRepository extends JpaRepository<Rules, String> {
    
}
