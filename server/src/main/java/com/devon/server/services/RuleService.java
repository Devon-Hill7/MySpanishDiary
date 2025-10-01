package com.devon.server.services;

import com.devon.server.repositories.RuleRepository;
import com.devon.server.entities.Rules;
import com.devon.server.entities.Grammar_Lessons;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
   
    private RuleRepository ruleRepository;



    public RuleService(RuleRepository repo) {
        ruleRepository = repo;
    }

    public Rules getRuleById(String id) {
        return ruleRepository.findById(id).orElse(null);
    }

    public Grammar_Lessons getLesson(Rules rule) {
        Grammar_Lessons lesson = rule.getLesson();
        if(lesson == null) {
            return null;
        }
        return lesson;
    }
}
