package com.nicki.enterpriseprojektarbete.highscore;

import com.nicki.enterpriseprojektarbete.user.UserModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "high_scores")
public class HighScoreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(nullable = false)
    private int score;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;


    public HighScoreModel() {
    }

    public HighScoreModel(int id, UserModel user, int score, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.score = score;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "HighScoreModel{" +
                "id=" + id +
                ", user=" + user +
                ", score=" + score +
                ", createdAt=" + createdAt +
                '}';
    }
}
