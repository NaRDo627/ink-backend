package net.ink.api.reply.component;

import net.ink.api.common.DtoCreator;
import net.ink.api.reply.dto.ReplyLikesDto;
import net.ink.core.common.EntityCreator;
import net.ink.core.reply.entity.ReplyLikes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReplyLikesMapperTest {

    @Autowired
    private ReplyLikesMapper replyLikesMapper;

    private final ReplyLikes replyLikes = EntityCreator.createReplyLikesEntity();
    private final ReplyLikesDto replyLikesDto = DtoCreator.createReplyLikesDto();

    @Test
    public void 엔티티에서_DTO변환_테스트() {
        ReplyLikesDto mappedDto = replyLikesMapper.toDto(replyLikes);
        assertEquals(replyLikesDto.getReplyId(), mappedDto.getReplyId());
        assertEquals(replyLikesDto.getMemberId(), mappedDto.getMemberId());
        assertTrue(mappedDto.isLikes());
    }
}
