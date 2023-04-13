package net.ink.admin.dto.mapper;

import net.ink.admin.dto.MemberDto;
import net.ink.core.member.entity.Member;
import net.ink.core.member.entity.MemberAttendance;
import net.ink.core.member.entity.MemberSetting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {
    @Autowired
    private MemberMapper memberMapper;

    private final Member member = Member.builder()
            .memberId(1L)
            .identifier("123")
            .email("test@gmail.com")
            .isActive(true)
            .nickname("Test")
            .regDate(LocalDateTime.of(2020, 10, 14, 17, 11, 9))
            .modDate(LocalDateTime.of(2020, 10, 14, 17, 11, 10))
            .memberAttendance(MemberAttendance.builder()
                    .attendanceCount(10)
                    .lastAttendanceDate(LocalDate.of(2020, 10, 14))
                    .build())
            .memberSetting(MemberSetting.builder()
                    .isLikePushActive(true)
                    .isDailyPushActive(false)
                    .build())
            .inkCookies(Set.of())
            .build();

    private final MemberDto memberDto = MemberDto.builder()
            .memberId(1L)
            .identifier("123")
            .email("test@gmail.com")
            .nickname("Test")
            .inkCount(0)
            .attendanceCount(10)
            .lastAttendanceDate("2020-10-14")
            .build();

    @Test
    void DTO로_변환_테스트() {
        MemberDto mappedDto = memberMapper.toDto(member);
        assertEquals(mappedDto.getMemberId(), memberDto.getMemberId());
        assertEquals(mappedDto.getIdentifier(), memberDto.getIdentifier());
        assertEquals(mappedDto.getImage(), memberDto.getImage());
        assertEquals(mappedDto.getEmail(), memberDto.getEmail());
        assertEquals(mappedDto.getNickname(), memberDto.getNickname());
        assertEquals(mappedDto.getInkCount(), memberDto.getInkCount());
        assertEquals(mappedDto.getAttendanceCount(), memberDto.getAttendanceCount());
        assertEquals(mappedDto.getLastAttendanceDate(), memberDto.getLastAttendanceDate());
    }
}