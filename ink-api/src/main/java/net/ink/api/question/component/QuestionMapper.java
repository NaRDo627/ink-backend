package net.ink.api.question.component;

import net.ink.api.member.component.MemberMapper;
import net.ink.core.member.entity.Member;
import net.ink.api.question.dto.QuestionDto;
import net.ink.core.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { WordHintMapper.class, MemberMapper.class })
public interface QuestionMapper {
    @Mapping(target = "repliesCount", expression = "java(question.getReplies().size())")
    QuestionDto.ReadOnly toDto(Question question);

    @Mapping(target = "regDate", ignore = true)
    Question toEntity(QuestionDto questionDto, Member author);
}
