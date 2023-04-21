package net.ink.admin.service.view;

import lombok.RequiredArgsConstructor;
import net.ink.admin.dto.MainViewDto;
import net.ink.admin.dto.mapper.UsefulExpressionMapper;
import net.ink.core.member.entity.Member;
import net.ink.core.member.repository.MemberRepository;
import net.ink.core.question.repository.QuestionRepository;
import net.ink.core.question.service.QuestionService;
import net.ink.core.question.service.TodayQuestionService;
import net.ink.core.reply.repository.ReplyRepository;
import net.ink.core.todayexpression.repository.TodayUsefulExpressionRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainViewService {
    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final TodayUsefulExpressionRepository todayUsefulExpressionRepository;
    private final UsefulExpressionMapper usefulExpressionMapper;

    public MainViewDto getDashboardItem() {
        return MainViewDto.builder()
                .questionCount((int) questionRepository.count())
                .registeredReplyCount((int) replyRepository.count())
                .totalMemberCount((int) memberRepository.findAll().stream().filter(Member::getIsActive).count())
                .todayUsefulExpressions(todayUsefulExpressionRepository.findAll()
                        .stream().map(x -> usefulExpressionMapper.toDto(x.getUsefulExpression())).collect(Collectors.toList()))
                .build();
    }
}
