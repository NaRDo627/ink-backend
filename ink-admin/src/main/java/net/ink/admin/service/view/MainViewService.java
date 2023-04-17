package net.ink.admin.service.view;

import lombok.RequiredArgsConstructor;
import net.ink.admin.dto.MainViewDto;
import net.ink.core.member.entity.Member;
import net.ink.core.member.repository.MemberRepository;
import net.ink.core.question.repository.QuestionRepository;
import net.ink.core.question.service.QuestionService;
import net.ink.core.question.service.TodayQuestionService;
import net.ink.core.reply.repository.ReplyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainViewService {
    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    public MainViewDto getDashboardItem() {
        return MainViewDto.builder()
                .questionCount((int) questionRepository.count())
                .registeredReplyCount((int) replyRepository.count())
                .totalMemberCount((int) memberRepository.findAll().stream().filter(Member::getIsActive).count())
                .build();
    }
}
