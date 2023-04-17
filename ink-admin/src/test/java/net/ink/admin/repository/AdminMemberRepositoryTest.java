package net.ink.admin.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.ink.admin.entity.AdminMember;
import net.ink.core.annotation.InkDataTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static net.ink.admin.entity.AdminMember.RANK.PENDING;
import static net.ink.admin.entity.AdminMember.RANK.SUPERVISOR;
import static org.junit.jupiter.api.Assertions.*;

@InkDataTest
class AdminMemberRepositoryTest {
    @Autowired
    AdminMemberRepository adminMemberRepository;

    @Test
    @DatabaseSetup({
            "classpath:dbunit/entity/admin_member.xml",
    })
    void findByUsername() {
        Optional<AdminMember> result = adminMemberRepository.findByUsername("admin1");
        assertTrue(result.isPresent());
        assertEquals("Admin One", result.get().getNickname());
        assertEquals(SUPERVISOR, result.get().getRank());
    }

    @Test
    @DatabaseSetup({
            "classpath:dbunit/entity/admin_member.xml",
    })
    void findByUsernameAndRankNot() {
        Optional<AdminMember> result = adminMemberRepository.findByUsernameAndRankNot("admin1", SUPERVISOR);
        assertFalse(result.isPresent());
        result = adminMemberRepository.findByUsernameAndRankNot("admin1", PENDING);
        assertTrue(result.isPresent());
        assertEquals("Admin One", result.get().getNickname());
        assertEquals(SUPERVISOR, result.get().getRank());
    }

    @Test
    @DatabaseSetup({
            "classpath:dbunit/entity/admin_member.xml",
    })
    void existsByUsernameAndIsActive() {
        assertTrue(adminMemberRepository.existsByUsernameAndIsActive("admin1", true));
        assertFalse(adminMemberRepository.existsByUsernameAndIsActive("admin1", false));
        assertFalse(adminMemberRepository.existsByUsernameAndIsActive("nonexistent", true));
    }

    @Test
    @DatabaseSetup({
            "classpath:dbunit/entity/admin_member.xml",
    })
    void existsByNicknameAndIsActive() {
        assertTrue(adminMemberRepository.existsByNicknameAndIsActive("Admin One", true));
        assertFalse(adminMemberRepository.existsByNicknameAndIsActive("Admin One", false));
        assertFalse(adminMemberRepository.existsByNicknameAndIsActive("Nonexistent", true));
    }
}