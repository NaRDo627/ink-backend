package net.ink.admin.repository;

import net.ink.admin.entity.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {
    Optional<AdminMember> findByUsername(String username);
    boolean existsByUsernameAndIsActive(String username, Boolean active);
    boolean existsByNicknameAndIsActive(String nickname, Boolean active);
}
