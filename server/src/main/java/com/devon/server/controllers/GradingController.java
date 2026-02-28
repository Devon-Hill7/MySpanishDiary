package com.devon.server.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.GradingService;
import com.devon.server.services.GrammarLessonService;
import com.devon.server.dtos.GradingRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;
import com.devon.server.entities.Grammar_Lessons;
import org.languagetool.rules.Rule;
import org.springframework.web.bind.annotation.CrossOrigin;



@CrossOrigin(origins = "${frontend.url}")
@RestController
public class GradingController {
    
    private final GradingService gradingService;
    private final GrammarLessonService grammarLessonService;

    public GradingController(GradingService gradingService, GrammarLessonService grammarLessonService) {
        this.gradingService = gradingService;
        this.grammarLessonService = grammarLessonService;
    }

    @PostMapping("/grade")
    public List<Grammar_Lessons> grade(@RequestBody GradingRequest request) throws Exception {
        List<RuleMatch> errors = gradingService.gradeText(request.getText());
        List<Grammar_Lessons> lessons = grammarLessonService.curateLessons(errors);
        return lessons;
        
    }

    /* 
    @GetMapping("/rules")
    public List<String> getRules() throws IOException {
        JLanguageTool langTool = new JLanguageTool(new Spanish());
        List<String> rules = new ArrayList<>();
        for (Rule rule : langTool.getAllRules()) {
            rules.add(rule.getId() + " -> " + rule.getDescription());
        };
        return rules;
    }
    */

}
