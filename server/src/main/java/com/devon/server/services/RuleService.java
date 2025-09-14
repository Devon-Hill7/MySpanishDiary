package com.devon.server.services;

import com.devon.server.repositories.RuleRepository;
import com.devon.server.entities.Rule;
import com.devon.server.entities.GrammarLesson;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
   
    private RuleRepository ruleRepository;



    public RuleService(RuleRepository repo) {
        ruleRepository = repo;
    }

    public Rule getRuleById(String id) {
        return ruleRepository.findById(id).orElse(null);
    }

    public GrammarLesson getLesson(Rule rule) {
        return rule.getLesson();
    }
}
