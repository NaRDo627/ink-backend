package net.ink.core.member.repository;

import net.ink.core.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNicknameAndIsActive(String nickname, Boolean active);
//    boolean existsByEmail(String email);
    Optional<Member> findByIdentifier(String identifier);
    Optional<Member> findByIdentifierAndIsActive(String identifier, Boolean active);
    boolean existsByIdentifier(String identifier);
    boolean existsByIdentifierAndIsActive(String identifier, Boolean active);
}
