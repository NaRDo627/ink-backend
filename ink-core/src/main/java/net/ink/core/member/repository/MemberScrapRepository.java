package net.ink.core.member.repository;

import net.ink.core.member.entity.MemberScrap;
import net.ink.core.member.entity.MemberScrapPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberScrapRepository extends JpaRepository<MemberScrap, MemberScrapPK> {
    List<MemberScrap> findAllByIdMemberId(Long memberId);
    Long countByMemberMemberId(Long memberId);
}
