package com.devon.server.services;

import com.devon.server.repositories.RuleRepository;
import com.devon.server.entities.Rule;

public class RuleService {
   
    private RuleRepository ruleRepository;

    public RuleService(RuleRepository repo) {
        ruleRepository = repo;
    }

    public Rule getRuleId(String id) {
        return ruleRepository.findById(id).orElse(null);
    }
}
