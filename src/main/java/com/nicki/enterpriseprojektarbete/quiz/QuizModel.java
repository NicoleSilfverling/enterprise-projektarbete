package com.nicki.enterpriseprojektarbete.quiz;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz")
public class QuizModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int nrOfQuestions;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private String category;

    public QuizModel() {
    }

    public QuizModel(int nrOfQuestions, String difficulty, String category) {
        this.nrOfQuestions = nrOfQuestions;
        this.difficulty = difficulty;
        this.category = category;
    }

    public long getId() {
        return id;
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
