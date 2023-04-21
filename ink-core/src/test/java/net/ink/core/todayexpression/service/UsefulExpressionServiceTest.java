package net.ink.core.todayexpression.service;

import net.ink.core.todayexpression.entity.TodayUsefulExpression;
import net.ink.core.todayexpression.entity.UsefulExpression;
import net.ink.core.todayexpression.repository.TodayUsefulExpressionRepository;
import net.ink.core.todayexpression.repository.UsefulExpressionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsefulExpressionServiceTest {
    @InjectMocks
    UsefulExpressionService usefulExpressionService;

    @Mock
    UsefulExpressionRepository usefulExpressionRepository;

    @Mock
    TodayUsefulExpressionRepository todayUsefulExpressionRepository;

    @Test
    void deleteByIdDirectDeletion() {
        // 오늘의 표현 목록에 없을 경우
        when(todayUsefulExpressionRepository.findAll())
                .thenReturn(List.of(
                        TodayUsefulExpression.builder().usefulExpression(UsefulExpression.builder().expId(2L).build()).build(),
                        TodayUsefulExpression.builder().usefulExpression(UsefulExpression.builder().expId(3L).build()).build()
                ));

        usefulExpressionService.deleteById(1L);

        verify(usefulExpressionRepository, times(1)).deleteById(eq(1L));
        verify(usefulExpressionRepository, times(0)).findTopByExpIdNotIn(any());
        verify(todayUsefulExpressionRepository, times(0)).save(any());
    }

    @Test
    void deleteByIdDeletionAfterChange() {
        // 오늘의 표현 목록에 있을 경우
        when(todayUsefulExpressionRepository.findAll())
                .thenReturn(List.of(
                        TodayUsefulExpression.builder().usefulExpression(UsefulExpression.builder().expId(1L).build()).build(),
                        TodayUsefulExpression.builder().usefulExpression(UsefulExpression.builder().expId(2L).build()).build()
                ));
        when(usefulExpressionRepository.findTopByExpIdNotIn(any()))
                .thenReturn(Optional.of(UsefulExpression.builder().expId(2L).build()));

        usefulExpressionService.deleteById(1L);

        verify(usefulExpressionRepository, times(1)).deleteById(eq(1L));
        verify(usefulExpressionRepository, times(2)).findTopByExpIdNotIn(any());
        verify(todayUsefulExpressionRepository, times(1)).save(any());
    }
}