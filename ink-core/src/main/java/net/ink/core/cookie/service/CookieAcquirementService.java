package net.ink.core.cookie.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.cookie.entity.CookieAcquirement;
import net.ink.core.cookie.repository.CookieAcquirementRepository;
import net.ink.core.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CookieAcquirementService {
    private final CookieAcquirementRepository cookieRepository;

    @Transactional
    public void acquireInkCookie(Member member){
        CookieAcquirement cookie = CookieAcquirement.builder().member(member).build();

        cookieRepository.saveAndFlush(cookie);
    }

    @Transactional(readOnly = true)
    public boolean isAlreadyCookieAcquiredToday(Long memberId) {
        LocalDate date = LocalDate.now();

        return cookieRepository.existsByRegDateAndMemberMemberId(date, memberId);
    }
}
