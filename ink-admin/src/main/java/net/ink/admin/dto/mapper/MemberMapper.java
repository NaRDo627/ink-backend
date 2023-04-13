package net.ink.admin.dto.mapper;

import net.ink.admin.dto.MemberDto;
import net.ink.core.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = DateTimeFormatter.class)
public interface MemberMapper {
    @Mapping(target = "inkCount", expression = "java(member.getInkCookies().isEmpty() ? 0 : member.getInkCookies().size())")
    @Mapping(target = "attendanceCount", expression = "java(member.getMemberAttendance().getAttendanceCount())")
    @Mapping(target = "lastAttendanceDate", expression = "java(member.getMemberAttendance().getLastAttendanceDate()" +
            ".format(DateTimeFormatter.ofPattern(\"yyyy-MM-dd\")) )")
    MemberDto toDto(Member member);
}
