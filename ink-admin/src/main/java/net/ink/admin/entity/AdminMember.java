package net.ink.admin.entity;

import lombok.*;
import net.ink.core.cookie.entity.CookieAcquirement;
import net.ink.core.member.entity.MemberAttendance;
import net.ink.core.member.entity.MemberSetting;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_member")
public class AdminMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id", nullable = false)
    private Long adminId;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false, unique = true)
    private String nickname;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RANK rank = RANK.PENDING;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @Builder.Default
    @UpdateTimestamp
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate = LocalDateTime.now();

    public enum RANK {
        PENDING,
        MANAGER,
        SUPERVISOR
    }
}
