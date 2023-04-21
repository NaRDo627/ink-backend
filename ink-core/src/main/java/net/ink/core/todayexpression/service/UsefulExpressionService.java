package net.ink.core.todayexpression.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.todayexpression.entity.TodayUsefulExpression;
import net.ink.core.todayexpression.entity.UsefulExpression;
import net.ink.core.todayexpression.repository.TodayUsefulExpressionRepository;
import net.ink.core.todayexpression.repository.UsefulExpressionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsefulExpressionService {
    private final UsefulExpressionRepository usefulExpressionRepository;
    private final TodayUsefulExpressionRepository todayUsefulExpressionRepository;

    @Transactional
    public void addUsefulExpression(UsefulExpression usefulExpression) {
        usefulExpressionRepository.save(usefulExpression);
    }

    /**
     * 매일 변경하는 로직
     */
    @Transactional(readOnly = true)
    public List<UsefulExpression> getTodayExpressions(){
        return todayUsefulExpressionRepository.findAll().stream()
                .map(TodayUsefulExpression::getUsefulExpression)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long expId){
        List<TodayUsefulExpression> todayUsefulExpressions = todayUsefulExpressionRepository.findAll();
        Optional<TodayUsefulExpression> todayUsefulExpression = todayUsefulExpressions.stream().filter(x -> Objects.equals(x.getUsefulExpression().getExpId(), expId)).findFirst();
        if (todayUsefulExpression.isEmpty()) {
            usefulExpressionRepository.deleteById(expId);
            return;
        }

        Optional<UsefulExpression> anotherUsefulExpression = usefulExpressionRepository.findTopByExpIdNotIn(Set.of(expId));
        if (anotherUsefulExpression.isEmpty()) {
            throw new IllegalStateException("No other useful expressions available to replace the current one");
        }

        Optional<UsefulExpression> finalAnotherUsefulExpression = anotherUsefulExpression;
        if (todayUsefulExpressions.stream().anyMatch(x -> Objects.equals(x.getUsefulExpression().getExpId(), finalAnotherUsefulExpression.get().getExpId()))) {
            anotherUsefulExpression = usefulExpressionRepository.findTopByExpIdNotIn(Set.of(expId, finalAnotherUsefulExpression.get().getExpId()));
        }

        if (anotherUsefulExpression.isPresent()) {
            TodayUsefulExpression tue = todayUsefulExpression.get();
            tue.setUsefulExpression(anotherUsefulExpression.get());
            todayUsefulExpressionRepository.save(tue);
            todayUsefulExpressionRepository.flush();

            usefulExpressionRepository.deleteById(expId);
        } else {
            throw new IllegalStateException("No other useful expressions available to replace the current one");
        }
    }
}
