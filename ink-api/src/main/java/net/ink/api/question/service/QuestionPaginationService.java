package net.ink.api.question.service;

import lombok.RequiredArgsConstructor;
import net.ink.api.core.dto.ApiPageRequest;
import net.ink.core.question.entity.Question;
import net.ink.core.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionPaginationService {
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Page<Question> getQuestionList(ApiPageRequest pageRequest) {
        if(pageRequest.getSort() == ApiPageRequest.PageSort.POPULAR)
            return questionRepository.findAllOrderByRepliesSizeDesc(pageRequest.convert());

        return questionRepository.findAll(pageRequest.convertWithNewestSort());
    }
}
