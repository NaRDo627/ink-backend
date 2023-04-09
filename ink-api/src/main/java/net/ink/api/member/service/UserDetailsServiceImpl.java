package net.ink.api.member.service;

import lombok.RequiredArgsConstructor;
import net.ink.api.core.dto.UserDetailsImpl;
import net.ink.core.member.entity.Member;
import net.ink.core.member.service.MemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberService memberService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        Member member = memberService.findByIdentifier(identifier);

        return new UserDetailsImpl(member);
    }
}

