package net.ink.core.member.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.core.exception.BadRequestException;
import net.ink.core.core.exception.ResourceNotFoundException;
import net.ink.core.member.entity.Member;
import net.ink.core.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import java.util.List;

import static net.ink.core.core.message.ErrorMessage.DUPLICATED_NICKNAME;
import static net.ink.core.core.message.ErrorMessage.NOT_EXIST_MEMBER;

@Validated
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(@Valid Member newMember) {
        if (isNicknameDuplicated(newMember.getNickname())) {
            throw new BadRequestException(DUPLICATED_NICKNAME);
        }

        return memberRepository.saveAndFlush(newMember);
    }

    @Transactional
    public Member updateMember(@Valid Member member) {
        return memberRepository.saveAndFlush(member);
    }

    @Transactional
    public void dropOutMember(@Valid Member member) {
        member.setIsActive(false);
        memberRepository.saveAndFlush(member);
    }

    @Transactional(readOnly = true)
    public boolean isNicknameDuplicated(String nickname) {
        return memberRepository.existsByNicknameAndIsActive(nickname, true);
    }

    @Transactional(readOnly = true)
    public boolean isMemberExist(String identifier) { return memberRepository.existsByIdentifierAndIsActive(identifier, true); }

    @Transactional(readOnly = true)
    public boolean isMemberExistIncludingDropped(String identifier) { return memberRepository.existsByIdentifier(identifier); }

    @Transactional(readOnly = true)
    public List<Member> findAllActiveMembers() { return memberRepository.findAllByIsActive(true); }

    @Transactional(readOnly = true)
    public Member findByIdentifier(String identifier) { return memberRepository.findByIdentifierAndIsActive(identifier, true)
            .orElseThrow(() -> new ResourceNotFoundException(NOT_EXIST_MEMBER)); }

    @Transactional(readOnly = true)
    public Member findByIdentifierIncludingDropped(String identifier) { return memberRepository.findByIdentifier(identifier)
            .orElseThrow(() -> new ResourceNotFoundException(NOT_EXIST_MEMBER)); }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) { return memberRepository.findById(memberId)
            .orElseThrow(() -> new ResourceNotFoundException(NOT_EXIST_MEMBER)); }
}
