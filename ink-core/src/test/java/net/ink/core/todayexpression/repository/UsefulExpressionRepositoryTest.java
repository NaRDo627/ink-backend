package net.ink.core.todayexpression.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.ink.core.annotation.InkDataTest;
import net.ink.core.todayexpression.entity.UsefulExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@InkDataTest
@DatabaseSetup({
        "classpath:dbunit/entity/member.xml",
        "classpath:dbunit/entity/useful_expression.xml",
})
class UsefulExpressionRepositoryTest {
    @Autowired
    UsefulExpressionRepository usefulExpressionRepository;

    @Test
    void findTopByExpIdNot() {
        UsefulExpression usefulExpression = usefulExpressionRepository.findTopByExpIdNotIn(Set.of(1L)).orElseThrow();
        assertEquals(2L, usefulExpression.getExpId());
    }
}