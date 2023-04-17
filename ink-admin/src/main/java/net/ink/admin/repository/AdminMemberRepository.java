package net.ink.admin.repository;

import net.ink.admin.entity.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {
    Optional<AdminMember> findByUsername(String username);
    Optional<AdminMember> findByUsernameAndRankNot(String username, AdminMember.RANK rank);
    boolean existsByUsernameAndIsActive(String username, Boolean active);
    boolean existsByNicknameAndIsActive(String nickname, Boolean active);
}
