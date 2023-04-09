package net.ink.api.member.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.member.entity.Member;
import net.ink.core.member.service.MemberService;
import net.ink.core.question.entity.Question;
import net.ink.core.question.entity.TodayQuestion;
import net.ink.core.question.repository.TodayQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
@RequiredArgsConstructor
public class MemberSignupService {
    private final MemberService memberService;
    private final TodayQuestionRepository todayQuestionRepository;

    @Transactional
    public Member signup(@Valid Member newMember) {
        if (isDroppedOutMember(newMember)) {
            Member member = memberService.findByIdentifierIncludingDropped(newMember.getIdentifier());
            member.updateMember(newMember);
            member.setIsActive(true);
            return memberService.updateMember(member);
        }

        Member savedMember = memberService.saveMember(newMember);
        afterSignUp(savedMember);
        return savedMember;
    }
    
    private boolean isDroppedOutMember(Member member) {
        return memberService.isMemberExistIncludingDropped(member.getIdentifier());
    }

    private void afterSignUp(Member newMember) {
        saveTodayQuestionForNewMember(newMember);
    }

    private void saveTodayQuestionForNewMember(Member newMember) {
        todayQuestionRepository.save(TodayQuestion.builder()
                .member(newMember)
                .question(Question.builder().questionId(1L).build()) // question_id 가 1인 질문이 있다는 가정
                .build());
    }
}
