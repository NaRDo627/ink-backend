package net.ink.admin.dto;

import lombok.Getter;
import lombok.Setter;
import net.ink.admin.entity.AdminMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter @Setter
public class AdminUser extends User {
    private AdminMember adminMember;

    public AdminMember.RANK getRank() {
        return adminMember.getRank();
    }

    public AdminUser(AdminMember adminMember, Collection<? extends GrantedAuthority> authorities) {
        super(adminMember.getEmail(), adminMember.getPassword(), authorities);
        this.adminMember = adminMember;
    }

    public AdminUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AdminUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
