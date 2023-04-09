package net.ink.api.question.component;

import net.ink.api.question.dto.WordHintDto;
import net.ink.core.question.entity.WordHint;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WordHintMapper {
    WordHintDto.ReadOnly toDto(WordHint wordHint);

    WordHint toEntity(WordHintDto wordHintDto);
}
