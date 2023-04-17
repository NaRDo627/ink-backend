package net.ink.admin.service;

import lombok.RequiredArgsConstructor;
import net.ink.admin.dto.AdminUser;
import net.ink.admin.entity.AdminMember;
import net.ink.admin.repository.AdminMemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminMemberRepository adminMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminMember adminMember = adminMemberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        // Add roles/authorities if necessary
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        AdminUser user = new AdminUser(adminMember.getNickname(), adminMember.getPassword(), authorities);
        user.setRank(adminMember.getRank());
        return user;
    }
}

