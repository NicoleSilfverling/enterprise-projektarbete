package com.nicki.enterpriseprojektarbete.trivia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriviaRepo extends JpaRepository<TriviaModel, Long> {
}
