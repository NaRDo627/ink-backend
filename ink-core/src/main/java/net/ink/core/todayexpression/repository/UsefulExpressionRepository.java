package net.ink.core.todayexpression.repository;

import net.ink.core.todayexpression.entity.UsefulExpression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsefulExpressionRepository extends JpaRepository<UsefulExpression, Long> {
}
