package net.ink.core.cookie.entity;

import lombok.*;
import net.ink.core.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cookie_acquirement")
public class CookieAcquirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder.Default
    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate = LocalDate.now();
}
