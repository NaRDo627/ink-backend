package net.ink.scheduler.todayexpression.maintain.service;


import net.ink.core.todayexpression.repository.TodayUsefulExpressionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql("classpath:local-test-data.sql")
@SpringBootTest
@DirtiesContext
class TodayUsefulExpressionMaintainServiceTest {
    @Autowired
    TodayUsefulExpressionRepository todayUsefulExpressionRepository;

    @Autowired
    TodayUsefulExpressionMaintainService todayUsefulExpressionMaintainService;

    @Test
    void 오늘의_표현_유지_테스트() {
        // given
        todayUsefulExpressionRepository.deleteAll(todayUsefulExpressionRepository.findAll());
        assertEquals(todayUsefulExpressionRepository.findAll().size(), 0);

        // when
        todayUsefulExpressionMaintainService.maintain();

        // then
        assertEquals(todayUsefulExpressionRepository.findAll().size(), 2);
    }
}