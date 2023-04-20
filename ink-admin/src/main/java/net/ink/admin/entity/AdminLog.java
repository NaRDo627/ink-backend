package net.ink.admin.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_log")
public class AdminLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_log_id", nullable = false)
    private Long adminId;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminMember actionedAdminMember;

    @NotNull
    @Column(nullable = false, unique = true)
    private String action;

    @NotNull
    @Column(nullable = false)
    private String actionQuery;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @Builder.Default
    @UpdateTimestamp
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate = LocalDateTime.now();
}
