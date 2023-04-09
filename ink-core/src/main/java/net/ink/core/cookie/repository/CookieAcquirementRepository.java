package net.ink.core.cookie.repository;

import net.ink.core.cookie.entity.CookieAcquirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CookieAcquirementRepository extends JpaRepository<CookieAcquirement, Long> {
    boolean existsByRegDateAndMemberMemberId(LocalDate date, Long memberId);
}
