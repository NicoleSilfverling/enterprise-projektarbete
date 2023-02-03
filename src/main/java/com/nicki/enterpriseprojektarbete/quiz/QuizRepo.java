package com.nicki.enterpriseprojektarbete.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<QuizModel, Long> {

}
