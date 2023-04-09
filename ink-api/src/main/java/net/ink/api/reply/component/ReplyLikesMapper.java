package net.ink.api.reply.component;

import net.ink.api.member.component.MemberMapper;
import net.ink.api.reply.dto.ReplyLikesDto;
import net.ink.core.reply.entity.ReplyLikes;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { ReplyMapper.class, MemberMapper.class })
public interface ReplyLikesMapper {

    @Mapping(source = "replyLikes.member.memberId", target = "memberId")
    @Mapping(source = "replyLikes.reply.replyId", target = "replyId")
    ReplyLikesDto toDto(ReplyLikes replyLikes);
}
