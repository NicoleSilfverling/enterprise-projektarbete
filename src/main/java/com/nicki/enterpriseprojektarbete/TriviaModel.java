package com.nicki.enterpriseprojektarbete;

import java.util.ArrayList;
import java.util.Arrays;

public class TriviaModel {
    private String category;
    private String id;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;
    private String question;
    private ArrayList<String> tags;
    private String type;
    private String difficulty;
    private ArrayList<String> regions;
    private boolean isNiche;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<String> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<String> regions) {
        this.regions = regions;
    }

    public boolean isNiche() {
        return isNiche;
    }

    public void setNiche(boolean niche) {
        isNiche = niche;
    }

    @Override
    public String toString() {
        return "TriviaModel{" +
                "category='" + category + '\'' +
                ", id='" + id + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                ", question='" + question + '\'' +
                ", tags=" + tags +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", regions=" + regions +
                ", isNiche=" + isNiche +
                '}';
    }
}
