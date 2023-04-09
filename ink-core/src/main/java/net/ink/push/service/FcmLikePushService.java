package net.ink.push.service;

import net.ink.core.member.entity.Member;
import net.ink.core.reply.entity.Reply;

public interface FcmLikePushService {
    void pushToAuthor(Reply reply, Member likedMember);
}
