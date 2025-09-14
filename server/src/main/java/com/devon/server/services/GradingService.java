package com.devon.server.services;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GradingService {
    private final JLanguageTool langTool;

    public GradingService() {
        langTool = new JLanguageTool(new Spanish());
    }

    public List<RuleMatch> gradeText(String text) throws Exception{
        System.out.print("Errors: " + langTool.check(text).toString());
        return langTool.check(text);
    }


}
