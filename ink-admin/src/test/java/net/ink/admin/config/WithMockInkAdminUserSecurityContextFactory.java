package net.ink.admin.config;

import net.ink.admin.annotation.WithMockInkAdminUser;
import net.ink.admin.dto.AdminUser;
import net.ink.admin.entity.AdminMember;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;

public class WithMockInkAdminUserSecurityContextFactory implements WithSecurityContextFactory<WithMockInkAdminUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockInkAdminUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Add roles/authorities if necessary
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));
        AdminUser principal =
                new AdminUser(AdminMember.builder()
                        .adminId(1L)
                        .email("test@email.com")
                        .nickname("테스트")
                        .rank(AdminMember.RANK.SUPERVISOR)
                        .password("password").build(), authorities);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}