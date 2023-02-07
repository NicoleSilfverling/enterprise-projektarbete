package com.nicki.enterpriseprojektarbete.quiz;


public enum Category {

    MUSIC,
    ARTS_AND_LITERATURE,
    FILM_AND_TV,
    FOOD_AND_DRINK,
    HISTORY,
    GEOGRAPHY,
    GENERAL_KNOWLEDGE,
    SCIENCE,
    SPORT_AND_LEISURE,
    SOCIETY_AND_CULTURE;

    public String getDisplayName() {
        String[] words = name().split("_");
        StringBuilder displayName = new StringBuilder();
        for (String word : words) {
            displayName.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
        }
        return displayName.toString().trim();
    }
}
