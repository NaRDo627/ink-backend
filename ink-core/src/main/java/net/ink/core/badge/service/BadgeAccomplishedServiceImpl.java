package net.ink.core.badge.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.badge.entity.BadgeAccomplished;
import net.ink.core.badge.entity.BadgeAccomplishedPK;
import net.ink.core.badge.repository.BadgeAccomplishedRepository;
import net.ink.core.core.exception.BadRequestException;
import net.ink.core.member.repository.MemberRepository;
import net.ink.core.member.repository.MemberScrapRepository;
import net.ink.core.member.service.MemberService;
import net.ink.core.reply.entity.Reply;
import net.ink.core.reply.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static net.ink.core.core.message.ErrorMessage.NOT_EXIST_REPLY;

@RequiredArgsConstructor
@Service
public class BadgeAccomplishedServiceImpl implements BadgeAccomplishedService {
    private final ReplyRepository replyRepository;
    private final MemberService memberService;
    private final BadgeService badgeService;
    private final BadgeAccomplishedRepository badgeAccomplishedRepository;
    private final MemberScrapRepository memberScrapRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public boolean createInk3Days(Long memberId) {
        if ( memberRepository.findById(memberId).get().getInkCookies().size() == Ink3Days.conditionOfCount &&
                !badgeAccomplishedRepository.existsBadgeAccomplishedByMemberMemberIdAndBadgeBadgeId(memberId, Ink3Days.id) ){

            badgeAccomplishedRepository.saveAndFlush(makeBadgeAccomplished(memberId, Ink3Days.id));
            return true;
        }

        return false;
    }

    @Transactional
    public boolean createCelebrityInk(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new BadRequestException(NOT_EXIST_REPLY));
        Long memberId = reply.getAuthor().getMemberId();

        if(reply.getReplyLikes().size() == CelebrityInk.conditionOfCount &&
                !badgeAccomplishedRepository.existsBadgeAccomplishedByMemberMemberIdAndBadgeBadgeId(memberId, CelebrityInk.id)) {

            badgeAccomplishedRepository.saveAndFlush(makeBadgeAccomplished(memberId, CelebrityInk.id));
            return true;
        }

        return false;
    }

    @Transactional
    public boolean createAcademicInk(Long memberId) {
        if( memberScrapRepository.countByMemberMemberId(memberId) == AcademicInk.conditionOfCount &&
                !badgeAccomplishedRepository.existsBadgeAccomplishedByMemberMemberIdAndBadgeBadgeId(memberId, AcademicInk.id ) ){

            badgeAccomplishedRepository.saveAndFlush(makeBadgeAccomplished(memberId, AcademicInk.id));
            return true;
        }

        return false;
    }

    @Transactional
    public boolean createInkSet(Long memberId){
        if ( memberRepository.findById(memberId).get().getInkCookies().size() == InkSet.conditionOfCount &&
                !badgeAccomplishedRepository.existsBadgeAccomplishedByMemberMemberIdAndBadgeBadgeId(memberId, InkSet.id) ){

            badgeAccomplishedRepository.saveAndFlush(makeBadgeAccomplished(memberId, InkSet.id));
            return true;
        }

        return false;
    }

    /**
     * 뱃지를 획득하지 않았거나 혹은 획득했는데 이미 확인한 경우
     */
    @Transactional(readOnly = true)
    public boolean isAlreadyCheckedCelebrityInk(Long memberId){
        BadgeAccomplishedPK pk = BadgeAccomplishedPK.builder().memberId(memberId).badgeId(CelebrityInk.id).build();
        Optional<BadgeAccomplished> badgeAccomplished = badgeAccomplishedRepository.findById(pk);

        if(badgeAccomplished.isEmpty() || badgeAccomplished.get().getIsChecked())
            return false;

        return true;
    }

    private BadgeAccomplished makeBadgeAccomplished(Long memberId, Long badgeId){
        BadgeAccomplishedPK badgeAccomplishedPK = BadgeAccomplishedPK.builder()
                .memberId(memberId)
                .badgeId(badgeId)
                .build();

        BadgeAccomplished badgeAccomplished = BadgeAccomplished.builder()
                .id(badgeAccomplishedPK)
                .member(memberService.findById(memberId))
                .badge(badgeService.findById(badgeId))
                .build();

        return badgeAccomplished;
    }


    public static class Ink3Days {
        public final static Long id = 1L;
        public final static int conditionOfCount = 3;
    }

    public static class CelebrityInk {
        public final static Long id = 2L;
        public final static int conditionOfCount = 10;
    }

    public static class AcademicInk {
        public final static Long id = 3L;
        public final static int conditionOfCount = 3;
    }

    public static class InkSet {
        public final static Long id = 4L;
        public final static int conditionOfCount = 30;
    }
}
