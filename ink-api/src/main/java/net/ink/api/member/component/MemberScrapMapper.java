package net.ink.api.member.component;

import net.ink.api.member.dto.MemberScrapDto;
import net.ink.api.todayexpression.component.TodayExpressionMapper;
import net.ink.core.member.entity.Member;
import net.ink.core.todayexpression.entity.UsefulExpression;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MemberMapper.class, TodayExpressionMapper.class})
public interface MemberScrapMapper {

    @Mapping(source = "member", target = "member")
    @Mapping(source = "scrapList", target = "scrapList")
    @Mapping(target = "requesterProfile", expression = "java(member.isRequesterProfile(loggedInMember.getMemberId()))")
    MemberScrapDto toDto(Member member, List<UsefulExpression> scrapList, @Context Member loggedInMember);
}
