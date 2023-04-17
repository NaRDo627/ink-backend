package net.ink.admin.service;

import lombok.RequiredArgsConstructor;
import net.ink.admin.entity.AdminMember;
import net.ink.admin.repository.AdminMemberRepository;
import net.ink.core.core.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import static net.ink.core.core.message.ErrorMessage.DUPLICATED_NICKNAME;

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
}
