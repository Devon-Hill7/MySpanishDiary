package com.devon.server.services;

import org.languagetool.JLanguageTool;
import org.languagetool.Languages;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GradingService {
    private final JLanguageTool langTool;

    public GradingService() {
        Language spanish = Languages.getLanguageForShortCode("es");
        langTool = new JLanguageTool(spanish);


    }

    public List<RuleMatch> gradeText(String text) throws Exception{
        List<RuleMatch> matches = langTool.check(text);
        for (RuleMatch match : matches) {
            System.out.println("Message: " + match.getMessage());
            System.out.println("Error at: " + match.getFromPos() + " - " + match.getToPos());
            System.out.println("Suggestions: " + match.getSuggestedReplacements());
        }
        return matches;
    }


}
