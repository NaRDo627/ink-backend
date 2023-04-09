package net.ink.core.badge.repository;

import net.ink.core.badge.entity.BadgeAccomplished;
import net.ink.core.badge.entity.BadgeAccomplishedPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgeAccomplishedRepository extends JpaRepository<BadgeAccomplished, BadgeAccomplishedPK> {
    List<BadgeAccomplished> findBadgeAccomplishedsByIdMemberId(Long memberId);
    boolean existsBadgeAccomplishedByMemberMemberIdAndBadgeBadgeId(Long memberId, Long badgeId);
}
