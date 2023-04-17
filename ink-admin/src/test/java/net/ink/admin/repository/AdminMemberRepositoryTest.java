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
        Optional<AdminMember> result = adminMemberRepository.findByEmail("admin1@exmaple.com");
        assertTrue(result.isPresent());
        assertEquals("Admin One", result.get().getNickname());
        assertEquals(SUPERVISOR, result.get().getRank());
    }

    @Test
    @DatabaseSetup({
            "classpath:dbunit/entity/admin_member.xml",
    })
    void findByEmailAndRankNot() {
        Optional<AdminMember> result = adminMemberRepository.findByEmailAndRankNot("admin1@exmaple.com", SUPERVISOR);
        assertFalse(result.isPresent());
        result = adminMemberRepository.findByEmailAndRankNot("admin1@exmaple.com", PENDING);
        assertTrue(result.isPresent());
        assertEquals("Admin One", result.get().getNickname());
        assertEquals(SUPERVISOR, result.get().getRank());
    }

    @Test
    @DatabaseSetup({
            "classpath:dbunit/entity/admin_member.xml",
    })
    void existsByEmailAndIsActive() {
        assertTrue(adminMemberRepository.existsByEmailAndIsActive("admin1@exmaple.com", true));
        assertFalse(adminMemberRepository.existsByEmailAndIsActive("admin1@exmaple.com", false));
        assertFalse(adminMemberRepository.existsByEmailAndIsActive("nonexistent@exmaple.com", true));
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