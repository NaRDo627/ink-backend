package net.ink.admin.dto.mapper;

import net.ink.admin.dto.WordHintDto;
import net.ink.core.question.entity.WordHint;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WordHintMapper {
    WordHintDto toDto(WordHint wordHint);
    WordHint toEntity(WordHintDto wordHintDto);
}
