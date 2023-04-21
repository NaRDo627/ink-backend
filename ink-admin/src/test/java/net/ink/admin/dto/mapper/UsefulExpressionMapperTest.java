package net.ink.admin.dto.mapper;

import net.ink.admin.dto.UsefulExpressionDto;
import net.ink.core.todayexpression.entity.UsefulExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsefulExpressionMapperTest {

    @Autowired
    UsefulExpressionMapper usefulExpressionMapper;

    @Test
    void toDtoTest() {
        UsefulExpression usefulExpression = UsefulExpression.builder()
                .expId(1L)
                .expression("Test expression")
                .meaning("Test meaning")
                .expressionExample("Test expression example")
                .expressionExampleMeaning("Test expression example meaning")
                .build();

        UsefulExpressionDto dto = usefulExpressionMapper.toDto(usefulExpression);

        assertEquals(usefulExpression.getExpId(), dto.getExpId());
        assertEquals(usefulExpression.getExpression(), dto.getExpression());
        assertEquals(usefulExpression.getMeaning(), dto.getMeaning());
        assertEquals(usefulExpression.getExpressionExample(), dto.getExpressionExample());
        assertEquals(usefulExpression.getExpressionExampleMeaning(), dto.getExpressionExampleMeaning());
    }

    @Test
    void toEntityTest() {
        UsefulExpressionDto dto = UsefulExpressionDto.builder()
                .expId(1L)
                .expression("Test expression")
                .meaning("Test meaning")
                .expressionExample("Test expression example")
                .expressionExampleMeaning("Test expression example meaning")
                .build();

        UsefulExpression usefulExpression = usefulExpressionMapper.toEntity(dto);

        assertEquals(dto.getExpId(), usefulExpression.getExpId());
        assertEquals(dto.getExpression(), usefulExpression.getExpression());
        assertEquals(dto.getMeaning(), usefulExpression.getMeaning());
        assertEquals(dto.getExpressionExample(), usefulExpression.getExpressionExample());
        assertEquals(dto.getExpressionExampleMeaning(), usefulExpression.getExpressionExampleMeaning());
    }
}