package net.ink.api.question.component;

import net.ink.api.common.DtoCreator;
import net.ink.api.question.dto.QuestionDto;
import net.ink.api.question.dto.WordHintDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionMapperTest {
    @Autowired
    QuestionMapper questionMapper;

    private final WordHint wordHint = WordHint.builder()
            .hintId(1L)
            .question(Question.builder().build())
            .word("apple")
            .meaning("사과")
            .regDate(LocalDateTime.parse("2020-10-14T11:00:00"))
            .build();

    private final WordHintDto.ReadOnly wordHintDto = WordHintDto.ReadOnly.builder()
            .hintId(1L)
            .word("apple")
            .meaning("사과")
            .build();

    private final List<Reply> replies = Arrays.asList(EntityCreator.createReplyEntity(), EntityCreator.createReplyEntity());

    private final Question question = Question.builder()
            .questionId(1L)
            .author(EntityCreator.createMemberEntity())
            .category("카테고리")
            .content("This is english content.")
            .koContent("이것은 한글 내용입니다.")
            .regDate(LocalDateTime.parse("2020-10-14T11:00:00"))
            .wordHints(Set.of(wordHint))
            .replies(replies)
            .build();

    private final QuestionDto.ReadOnly questionDto = QuestionDto.ReadOnly.builder()
            .questionId(1L)
            .author(DtoCreator.createMemberDto())
            .category("카테고리")
            .content("This is english content.")
            .koContent("이것은 한글 내용입니다.")
            .wordHints(Set.of(wordHintDto))
            .repliesCount(2)
            .build();

    @Test
    public void 엔티티에서_DTO변환_테스트() {
        QuestionDto.ReadOnly mappedDto = questionMapper.toDto(question);
        assertEquals(questionDto.getQuestionId(), mappedDto.getQuestionId());
        assertEquals(questionDto.getCategory(), mappedDto.getCategory());
        assertEquals(questionDto.getContent(), mappedDto.getContent());
        assertEquals(questionDto.getKoContent(), mappedDto.getKoContent());
        assertEquals(questionDto.getWordHints().size(), mappedDto.getWordHints().size());
        assertEquals(questionDto.getRepliesCount(), mappedDto.getRepliesCount());
        assertEquals(questionDto.getAuthor().getMemberId(), mappedDto.getAuthor().getMemberId());
    }

    @Test
    public void DTO에서_엔티티_사용자추가_변환_테스트() {
        Question mappedEntity = questionMapper.toEntity(questionDto, EntityCreator.createMemberEntity());
        assertEquals(question.getCategory(), mappedEntity.getCategory());
        assertEquals(question.getContent(), mappedEntity.getContent());
        assertEquals(question.getKoContent(), mappedEntity.getKoContent());
        assertEquals(question.getAuthor().getMemberId(), mappedEntity.getAuthor().getMemberId());
    }
}