package net.ink.core.reply.repository;

import net.ink.core.reply.entity.ReplyLikes;
import net.ink.core.reply.entity.ReplyLikesPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyLikesRepository extends JpaRepository<ReplyLikes, ReplyLikesPK> {
    int countByIdReplyId(Long replyId);
    Boolean existsByMemberMemberIdAndReplyReplyId(Long memberId, Long replyId);
}
