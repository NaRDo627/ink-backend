package net.ink.api.todayexpression.component;

import net.ink.api.todayexpression.dto.UsefulExpressionDto;
import net.ink.core.member.entity.Member;
import net.ink.core.todayexpression.entity.UsefulExpression;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodayExpressionMapper {

    @Mapping(target = "scrappedByRequester", expression = "java(usefulExpression.scrappedByRequester(loggedInMember))")
    UsefulExpressionDto toDto(UsefulExpression usefulExpression, @Context Member loggedInMember);
}
