package net.ink.admin.dto.mapper;

import net.ink.admin.dto.QuestionDto;
import net.ink.core.member.entity.Member;
import net.ink.core.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {  MemberMapper.class, MemberMapper.class })
public interface QuestionMapper {
    @Mapping(target = "repliesCount", expression = "java(question.getReplies().size())")
    @Mapping(target = "authorName", expression = "java(question.getAuthor() == null ? \"시스템\" : question.getAuthor().getNickname())")
    QuestionDto toDto(Question question);

    @Mapping(target = "regDate", ignore = true)
    Question toEntity(QuestionDto questionDto, Member author);
}
