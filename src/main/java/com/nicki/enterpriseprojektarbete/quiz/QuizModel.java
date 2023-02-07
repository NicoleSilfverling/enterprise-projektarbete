package com.nicki.enterpriseprojektarbete.quiz;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "quiz")
public class QuizModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, max = 50, message = "Title must be at least 2 characters")
    private String title;

    @Positive
    private int nrOfQuestions;

    @NotEmpty
    private String difficulty;

    @NotEmpty
    private String category;

    public QuizModel() {
    }

    public QuizModel(String title, int nrOfQuestions, String difficulty, String category) {
        this.title = title;
        this.nrOfQuestions = nrOfQuestions;
        this.difficulty = difficulty;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNrOfQuestions() {
        return nrOfQuestions;
    }

    public void setNrOfQuestions(int nrOfQuestions) {
        this.nrOfQuestions = nrOfQuestions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", nrOfQuestions=" + nrOfQuestions +
                ", difficulty='" + difficulty + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
