package net.ink.core.question.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.core.exception.ResourceNotFoundException;
import net.ink.core.question.entity.Question;
import net.ink.core.question.entity.TodayQuestion;
import net.ink.core.question.repository.TodayQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.ink.core.core.message.ErrorMessage.NOT_EXIST_QUESTION;

@Service
@RequiredArgsConstructor
public class TodayQuestionService {
    private final TodayQuestionRepository todayQuestionRepository;

    @Transactional(readOnly = true)
    public Question getTodayQuestionByMemberId(Long memberId) {
        return todayQuestionRepository.findById(memberId).orElseThrow(() -> new ResourceNotFoundException(NOT_EXIST_QUESTION)).getQuestion();
    }

    @Transactional
    public void saveTodayQuestion(Long memberId, Question question) {
        TodayQuestion todayQuestion = todayQuestionRepository.findById(memberId).orElseThrow(() -> new ResourceNotFoundException(NOT_EXIST_QUESTION));
        todayQuestion.setQuestion(question);
        todayQuestionRepository.saveAndFlush(todayQuestion);
    }
}
