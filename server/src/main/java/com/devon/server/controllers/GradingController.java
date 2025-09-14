package com.devon.server.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devon.server.services.GradingService;
import com.devon.server.services.GrammarLessonService;
import com.devon.server.dtos.GradingRequest;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.languagetool.rules.RuleMatch;
import com.devon.server.entities.GrammarLesson;

@RestController
public class GradingController {
    
    private final GradingService gradingService;
    private final GrammarLessonService grammarLessonService;

    public GradingController(GradingService gradingService, GrammarLessonService grammarLessonService) {
        this.gradingService = gradingService;
        this.grammarLessonService = grammarLessonService;
    }

    @PostMapping("/grade")
    public List<GrammarLesson> grade(@RequestBody GradingRequest request) throws Exception {
        List<RuleMatch> errors = gradingService.gradeText(request.getText());
        List<GrammarLesson> lessons = grammarLessonService.curateLessons(errors);
        return lessons;
        
    }
}
