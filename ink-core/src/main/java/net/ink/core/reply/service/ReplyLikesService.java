package net.ink.core.reply.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.badge.service.BadgeAccomplishedService;
import net.ink.core.core.exception.InkException;
import net.ink.core.member.entity.Member;
import net.ink.core.reply.entity.Reply;
import net.ink.core.reply.entity.ReplyLikes;
import net.ink.core.reply.entity.ReplyLikesPK;
import net.ink.core.reply.repository.ReplyLikesRepository;
import net.ink.push.service.FcmLikePushService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.ink.core.core.message.ErrorMessage.ALREADY_CANCELED_REPLY_LIKE;
import static net.ink.core.core.message.ErrorMessage.ALREADY_PUSHED_REPLY_LIKE;

@Service
@RequiredArgsConstructor
public class ReplyLikesService {
    private final ReplyLikesRepository replyLikesRepository;
    private final ReplyService replyService;
    private final BadgeAccomplishedService badgeAccomplishedService;
    private final FcmLikePushService fcmLikePushService;

    @Transactional
    public ReplyLikes postReplyLikes(Member member, Long replyId){
        Reply reply = replyService.findById(replyId);

        ReplyLikesPK replyLikesPK = new ReplyLikesPK(member.getMemberId(), reply.getReplyId());

        if(replyLikesRepository.existsById(replyLikesPK))
            throw new InkException(ALREADY_PUSHED_REPLY_LIKE);

        ReplyLikes replyLikes = ReplyLikes.builder().id(replyLikesPK).member(member).reply(reply).build();

        replyLikesRepository.saveAndFlush(replyLikes);

        badgeAccomplishedService.createCelebrityInk(replyId);
        fcmLikePushService.pushToAuthor(reply, member);

        return replyLikes;
    }

    @Transactional
    public void deleteReplyLikes(Member member, Long replyId){
        Reply reply = replyService.findById(replyId);

        ReplyLikesPK replyLikesPK = new ReplyLikesPK(member.getMemberId(), reply.getReplyId());

        if(! replyLikesRepository.existsById(replyLikesPK))
            throw new InkException(ALREADY_CANCELED_REPLY_LIKE);

        replyLikesRepository.deleteById(replyLikesPK);
    }

}
