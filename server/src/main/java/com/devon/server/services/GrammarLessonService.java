package com.devon.server.services;

import org.languagetool.rules.RuleMatch;

import com.devon.server.entities.GrammarLesson;
import com.devon.server.repositories.GrammarLessonRepository;
import com.devon.server.repositories.RuleRepository;
import com.devon.server.services.RuleService;
import com.devon.server.entities.Rule;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class GrammarLessonService {
    
    private final GrammarLessonRepository grammarLessonRepository;
    private final RuleService ruleService;

    public GrammarLessonService(GrammarLessonRepository repo, RuleService ruleService) {
        grammarLessonRepository = repo;
        this.ruleService = ruleService;
    }

    public GrammarLesson getLessonByRule(Rule rule) {
        return ruleService.getLesson(rule);
    }

    public List<GrammarLesson> curateLessons(List<RuleMatch> errors) {
        List<GrammarLesson> lessons = new ArrayList<>();
        for(RuleMatch error : errors) {
           Rule rule = ruleService.getRuleById(error.getRule().getId());
           GrammarLesson lesson = getLessonByRule(rule);
           if(lesson != null && !lessons.contains(lesson)) {
               lessons.add(lesson);
           }
        }
        return lessons;
    }
}
