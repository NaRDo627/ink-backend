package net.ink.admin.dto.mapper;

import net.ink.admin.dto.UsefulExpressionDto;
import net.ink.core.member.entity.Member;
import net.ink.core.todayexpression.entity.UsefulExpression;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsefulExpressionMapper {
    UsefulExpressionDto toDto(UsefulExpression usefulExpression);
    UsefulExpression toEntity(UsefulExpressionDto usefulExpression);
}
