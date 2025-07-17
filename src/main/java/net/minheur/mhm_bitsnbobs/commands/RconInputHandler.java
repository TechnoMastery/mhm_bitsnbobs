package net.minheur.mhm_bitsnbobs.commands;

import net.minheur.mhm_bitsnbobs.event.RconKeywordLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RconInputHandler {

    public static String analyzeText(String input) {
        List<String> detectedCategories = new ArrayList<>();
        String answer;

        for (Map.Entry<String, List<String>> entry : RconKeywordLoader.KEYWORDS.entrySet()) {
            for (String keyword : entry.getValue()) {
                if (input.contains(keyword)) {
                    detectedCategories.add(entry.getKey());
                    break;
                }
            }
            if (!detectedCategories.isEmpty()) break;
        }
        if (detectedCategories.isEmpty()) return null;

        answer = traduce(detectedCategories);

        return answer;
    }

    private static String traduce(List<String> category) {
        return RconAnswers.fromKey(category.get(0)).getAnswer();
    }
}
