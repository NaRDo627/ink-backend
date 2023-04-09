package net.ink.core.todayexpression.repository;

import net.ink.core.todayexpression.entity.TodayUsefulExpression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayUsefulExpressionRepository extends JpaRepository<TodayUsefulExpression, Long> {
}
