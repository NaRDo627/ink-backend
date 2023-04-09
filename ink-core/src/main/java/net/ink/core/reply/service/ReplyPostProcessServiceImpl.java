package net.ink.core.reply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.core.cookie.repository.CookieAcquirementRepository;
import net.ink.core.cookie.service.CookieAcquirementService;
import net.ink.core.member.entity.Member;
import net.ink.core.member.repository.MemberRepository;
import net.ink.core.reply.entity.Reply;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.ink.core.core.message.ErrorMessage.ALREADY_COOKIE_ACQUIRED;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyPostProcessServiceImpl implements ReplyPostProcessService {
    private final MemberRepository memberRepository;
    private final CookieAcquirementService cookieAcquirementService;
    private final CookieAcquirementRepository cookieAcquirementRepository;

    @Transactional
    public void postProcess(Reply reply) {
        Member member = reply.getAuthor();
        if( !cookieAcquirementService.isAlreadyCookieAcquiredToday(member.getMemberId()) ) {
            log.info(ALREADY_COOKIE_ACQUIRED);
            cookieAcquirementService.acquireInkCookie(member);
        }

        memberRepository.saveAndFlush(member);
    }
}
