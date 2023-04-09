package net.ink.core.member.repository;

import net.ink.core.member.entity.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
    boolean existsByToken(String token);
    Optional<FcmToken> findByMemberMemberId(Long memberId);
    Optional<FcmToken> findByToken(String token);
}
