package net.ink.core.todayexpression.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.todayexpression.entity.TodayUsefulExpression;
import net.ink.core.todayexpression.entity.UsefulExpression;
import net.ink.core.todayexpression.repository.TodayUsefulExpressionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsefulExpressionService {
    private final TodayUsefulExpressionRepository todayUsefulExpressionRepository;

    /**
     * 매일 변경하는 로직
     */
    @Transactional(readOnly = true)
    public List<UsefulExpression> getTodayExpressions(){
        return todayUsefulExpressionRepository.findAll().stream()
                .map(TodayUsefulExpression::getUsefulExpression)
                .collect(Collectors.toList());
    }
}
