package net.ink.core.todayexpression.entity;

import lombok.*;
import net.ink.core.member.entity.Member;
import net.ink.core.member.entity.MemberScrap;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "useful_expression")
public class UsefulExpression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id", nullable = false)
    private Long expId;

    @Column(nullable = false)
    private String expression;

    @Column(nullable = false)
    private String meaning;

    @Column(name = "expression_example", nullable = false)
    private String expressionExample;

    @Column(name = "expression_example_meaning", nullable = false)
    private String expressionExampleMeaning;

    @Builder.Default
    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate = LocalDateTime.now();

    @Builder.Default
    @OneToMany(mappedBy = "usefulExpression", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberScrap> memberScraps = new HashSet<>();

    public boolean scrappedByRequester(Member member){
        for(MemberScrap memberScrap : this.memberScraps){
            if(memberScrap.getId().getMemberId() == member.getMemberId())
                return true;
        }
        return false;
    }
}
