package net.ink.core.question.repository;

import net.ink.core.question.entity.TodayQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayQuestionRepository extends JpaRepository<TodayQuestion, Long> {
}
