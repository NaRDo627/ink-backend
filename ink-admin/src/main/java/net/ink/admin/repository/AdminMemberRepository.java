package net.ink.admin.repository;

import net.ink.admin.entity.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {
    Optional<AdminMember> findByEmail(String email);
    Optional<AdminMember> findByEmailAndRankNot(String email, AdminMember.RANK rank);
    boolean existsByEmailAndIsActive(String username, Boolean active);
    boolean existsByNicknameAndIsActive(String nickname, Boolean active);
}
