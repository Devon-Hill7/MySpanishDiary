package com.devon.server.services;

import org.languagetool.AnalyzedSentence;
import org.languagetool.rules.RuleMatch;

import com.devon.server.entities.Grammar_Lessons;
import com.devon.server.repositories.GrammarLessonRepository;
import com.devon.server.repositories.RuleRepository;
import com.devon.server.services.RuleService;
import com.devon.server.entities.Rules;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class GrammarLessonService {
    
    private final GrammarLessonRepository grammarLessonRepository;
    private final RuleService ruleService;

    public GrammarLessonService(GrammarLessonRepository repo, RuleService ruleService) {
        grammarLessonRepository = repo;
        this.ruleService = ruleService;
    }

    public Grammar_Lessons getLessonByRule(Rules rule) {
        return ruleService.getLesson(rule);
    }

    public List<Grammar_Lessons> curateLessons(List<RuleMatch> errors) {
        List<Grammar_Lessons> lessons = new ArrayList<>();
        for(RuleMatch error : errors) {
           Rules rule = ruleService.getRuleById(error.getRule().getId());
           if(rule != null) {
                Grammar_Lessons lesson = getLessonByRule(rule);
                if(lesson != null && !lessons.contains(lesson)) {
                    setErrorInfo(lesson, error);
                    lessons.add(lesson);
                }
           }
           else {
               Grammar_Lessons lesson = new Grammar_Lessons();
               lesson.setTitle("Miscellaneous");
               lesson.setVideoTitle(null);
               setErrorInfo(lesson, error);
               lessons.add(lesson);
           }
        }
        return lessons;
    }

    private void setErrorInfo(Grammar_Lessons lesson, RuleMatch error) {
        lesson.setIncorrectSentence(error.getSentence().getText());
        lesson.setSuggestions(error.getSuggestedReplacements());
        lesson.setErrorStartPos(error.getFromPos());
        lesson.setErrorEndPos(error.getToPos());
    }

}
