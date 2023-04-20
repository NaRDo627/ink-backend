package net.ink.admin.service;

import lombok.RequiredArgsConstructor;
import net.ink.admin.dto.AdminUser;
import net.ink.admin.entity.AdminMember;
import net.ink.admin.repository.AdminMemberRepository;
import net.ink.core.core.exception.BadRequestException;
import net.ink.core.core.exception.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import static net.ink.core.core.message.ErrorMessage.DUPLICATED_NICKNAME;
import static net.ink.core.core.message.ErrorMessage.NOT_EXIST_MEMBER;

@Service
@RequiredArgsConstructor
public class AdminMemberService {
    private final AdminMemberRepository adminMemberRepository;

    @Transactional
    public AdminMember saveAdminMember(@Valid AdminMember newMember) {
        if (isNicknameDuplicated(newMember.getNickname())) {
            throw new BadRequestException(DUPLICATED_NICKNAME);
        }

        if (isEmailDuplicated(newMember.getEmail())) {
            throw new BadRequestException(DUPLICATED_NICKNAME);
        }

        return adminMemberRepository.saveAndFlush(newMember);
    }

    @Transactional(readOnly = true)
    public boolean isNicknameDuplicated(String email) {
        return adminMemberRepository.existsByNicknameAndIsActive(email, true);
    }

    @Transactional(readOnly = true)
    public boolean isEmailDuplicated(String email) {
        return adminMemberRepository.existsByEmailAndIsActive(email, true);
    }
    @Transactional
    public void deleteAdminMemberById(Long adminId) {
        AdminMember adminMember = adminMemberRepository.findById(adminId).orElseThrow(() -> new EntityNotFoundException(NOT_EXIST_MEMBER));

        if (adminMember.getRank() == AdminMember.RANK.SUPERVISOR) {
            throw new BadRequestException("슈퍼바이저는 삭제할 수 없습니다.");
        }

        adminMember.setIsActive(false);
    }

    @Transactional
    public void promoteAdminMemberById(Long adminId) {
        AdminMember adminMember = adminMemberRepository.findById(adminId).orElseThrow(() -> new EntityNotFoundException(NOT_EXIST_MEMBER));
        if (adminMember.getRank() == AdminMember.RANK.PENDING) {
            adminMember.setRank(AdminMember.RANK.MANAGER);
        } else if (adminMember.getRank() == AdminMember.RANK.MANAGER) {
            adminMember.setRank(AdminMember.RANK.SUPERVISOR);
            AdminMember currentMember = ((AdminUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdminMember();
            currentMember = adminMemberRepository.findById(currentMember.getAdminId()).orElseThrow(() -> new EntityNotFoundException(NOT_EXIST_MEMBER));
            currentMember.setRank(AdminMember.RANK.MANAGER);

            SecurityContextHolder.clearContext();
        }
    }
}
