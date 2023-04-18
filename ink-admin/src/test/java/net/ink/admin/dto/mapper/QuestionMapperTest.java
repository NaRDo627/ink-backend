package net.ink.admin.dto.mapper;

import net.ink.admin.dto.QuestionDto;
import net.ink.core.common.EntityCreator;
import net.ink.core.question.entity.Question;
import net.ink.core.question.entity.WordHint;
import net.ink.core.reply.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionMapperTest {
    @Autowired
    QuestionMapper questionMapper;

    private final List<Reply> replies = Arrays.asList(EntityCreator.createReplyEntity(), EntityCreator.createReplyEntity());

    private final Question question = Question.builder()
            .questionId(1L)
            .author(EntityCreator.createMemberEntity())
            .category("카테고리")
            .content("This is english content.")
            .koContent("이것은 한글 내용입니다.")
            .regDate(LocalDateTime.parse("2020-10-14T11:00:00"))
            .replies(replies)
            .build();

    private final QuestionDto questionDto = QuestionDto.builder()
            .questionId(1L)
            .authorName("Test")
            .category("카테고리")
            .content("This is english content.")
            .koContent("이것은 한글 내용입니다.")
            .repliesCount(2)
            .build();

    @Test
    public void 엔티티에서_DTO변환_테스트() {
        QuestionDto mappedDto = questionMapper.toDto(question);
        assertEquals(questionDto.getQuestionId(), mappedDto.getQuestionId());
        assertEquals(questionDto.getCategory(), mappedDto.getCategory());
        assertEquals(questionDto.getContent(), mappedDto.getContent());
        assertEquals(questionDto.getKoContent(), mappedDto.getKoContent());
        assertEquals(questionDto.getRepliesCount(), mappedDto.getRepliesCount());
        assertEquals(questionDto.getAuthorName(), mappedDto.getAuthorName());
    }

    @Test
    public void DTO에서_엔티티_사용자추가_변환_테스트() {
        Question mappedEntity = questionMapper.toEntity(questionDto, EntityCreator.createMemberEntity());
        assertEquals(question.getCategory(), mappedEntity.getCategory());
        assertEquals(question.getContent(), mappedEntity.getContent());
        assertEquals(question.getKoContent(), mappedEntity.getKoContent());
    }
}