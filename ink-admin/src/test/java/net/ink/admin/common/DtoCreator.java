package net.ink.admin.common;

import net.ink.admin.dto.MemberDto;
import net.ink.admin.dto.QuestionDto;
import net.ink.admin.dto.WordHintDto;

import java.util.Set;

public class DtoCreator {
    private static final Long QUESTION_ID = 1L;
    private static final Long MEMBER_ID = 1L;
    private static final Long REPLY_ID = 1L;

    public static MemberDto createMemberDto() {
        return MemberDto.builder()
                .memberId(MEMBER_ID)
                .identifier("123")
                .email("test@gmail.com")
                .nickname("Test")
                .inkCount(0)
                .attendanceCount(10)
                .lastAttendanceDate("2020-10-14")
                .build();
    }

    public static QuestionDto createQuestionDto() {
        return QuestionDto.builder()
                .questionId(QUESTION_ID)
                .category("카테고리")
                .content("This is english content.")
                .koContent("이것은 한글 내용입니다.")
                .authorName("테스트")
                .wordHints(Set.of(createWordHintDto()))
                .repliesCount(2)
                .build();
    }

    public static WordHintDto createWordHintDto() {
        return WordHintDto.builder()
                .hintId(1L)
                .word("apple")
                .meaning("사과")
                .build();
    }
}
