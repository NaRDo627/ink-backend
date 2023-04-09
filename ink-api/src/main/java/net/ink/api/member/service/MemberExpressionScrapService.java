package net.ink.api.member.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.core.exception.BadRequestException;
import net.ink.core.badge.service.BadgeAccomplishedService;
import net.ink.core.core.exception.EntityNotFoundException;
import net.ink.core.member.entity.Member;
import net.ink.core.member.entity.MemberScrap;
import net.ink.core.member.entity.MemberScrapPK;
import net.ink.core.member.repository.MemberScrapRepository;
import net.ink.core.todayexpression.entity.UsefulExpression;
import net.ink.core.todayexpression.repository.UsefulExpressionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.ink.core.core.message.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class MemberExpressionScrapService {

    private final UsefulExpressionRepository usefulExpressionRepository;
    private final MemberScrapRepository memberScrapRepository;
    private final BadgeAccomplishedService badgeAccomplishedService;

    @Transactional
    public MemberScrap scrapTodayExpression(Member member, Long expId){
        UsefulExpression usefulExpression = usefulExpressionRepository.findById(expId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST_EXPRESSION));

        MemberScrapPK memberScrapPK = new MemberScrapPK(member.getMemberId(), usefulExpression.getExpId());

        if(memberScrapRepository.existsById(memberScrapPK))
            throw new BadRequestException(ALREADY_SCRAPPED_EXPRESSION);

        MemberScrap memberScrap = MemberScrap.builder().id(memberScrapPK).member(member).usefulExpression(usefulExpression).build();

        memberScrapRepository.saveAndFlush(memberScrap);

        return memberScrap;
    }

    @Transactional
    public void deleteScrap(Member member, Long expId) {
        UsefulExpression usefulExpression = usefulExpressionRepository.findById(expId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST_EXPRESSION));

        MemberScrapPK memberScrapPK = new MemberScrapPK(member.getMemberId(), usefulExpression.getExpId());

        if(!memberScrapRepository.existsById(memberScrapPK))
            throw new BadRequestException(ALREADY_CANCELED_EXPRESSION);

        memberScrapRepository.deleteById(memberScrapPK);
    }

    @Transactional(readOnly = true)
    public List<MemberScrap> getMemberScrap(Long memberId){
        return memberScrapRepository.findAllByIdMemberId(memberId);
    }

}
