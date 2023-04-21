package net.ink.core.todayexpression.repository;

import net.ink.core.todayexpression.entity.UsefulExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UsefulExpressionRepository extends JpaRepository<UsefulExpression, Long> {
    Optional<UsefulExpression> findTopByExpIdNotIn(Collection<Long> expId);
}
