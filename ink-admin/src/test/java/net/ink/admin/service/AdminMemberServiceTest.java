package net.ink.admin.service;

import net.ink.admin.entity.AdminMember;
import net.ink.admin.repository.AdminMemberRepository;
import net.ink.core.core.exception.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminMemberServiceTest {
    @Autowired
    AdminMemberService adminMemberService;

    @MockBean
    AdminMemberRepository adminMemberRepository;

    @Test
    @DisplayName("관리자 회원을 저장한다.")
    void saveAdminMember() {
        when(adminMemberRepository.saveAndFlush(any(AdminMember.class))).thenReturn(new AdminMember());
    }

    @Test
    @DisplayName("관리자 회원을 저장할 때 닉네임이 중복되면 실패한다.")
    void saveAdminMemberButNicknameDuplicatedWillFail() {
        when(adminMemberRepository.existsByNicknameAndIsActive(any(String.class), any(Boolean.class))).thenReturn(true);
        when(adminMemberRepository.saveAndFlush(any(AdminMember.class))).thenReturn(new AdminMember());

        assertThrows(BadRequestException.class, () -> adminMemberService.saveAdminMember(AdminMember.builder()
                .nickname("test")
                .email("test@email.com")
                .build()));
    }

    @Test
    @DisplayName("관리자 회원을 저장할 때 이메일이 중복되면 실패한다.")
    void saveAdminMemberButEmailDuplicatedWillFail() {
        when(adminMemberRepository.existsByNicknameAndIsActive(any(String.class), any(Boolean.class))).thenReturn(true);
        when(adminMemberRepository.existsByEmailAndIsActive(any(String.class), any(Boolean.class))).thenReturn(true);
        when(adminMemberRepository.saveAndFlush(any(AdminMember.class))).thenReturn(new AdminMember());

        assertThrows(BadRequestException.class, () -> adminMemberService.saveAdminMember(AdminMember.builder()
                .nickname("test")
                .email("test@email.com")
                .build()));
    }

    @Test
    @DisplayName("관리자 회원을 삭제한다.")
    void deleteAdminMemberById() {
        when(adminMemberRepository.findById(eq(1L))).thenReturn(java.util.Optional.of(
                AdminMember.builder()
                        .nickname("test")
                        .email("test@email.com")
                        .rank(AdminMember.RANK.PENDING)
                        .build())
        );

        adminMemberService.deleteAdminMemberById(1L);
    }

    @Test
    @DisplayName("관리자 회원을 삭제할 때 SUPERVISOR는 삭제할 수 없다.")
    void deleteAdminMemberByIdButSupervisorCannotBeDeleted() {
        when(adminMemberRepository.findById(eq(1L))).thenReturn(java.util.Optional.of(
                AdminMember.builder()
                        .nickname("test")
                        .email("test@email.com")
                        .rank(AdminMember.RANK.SUPERVISOR)
                        .build())
        );

        assertThrows(BadRequestException.class, () -> adminMemberService.deleteAdminMemberById(1L));
    }
}