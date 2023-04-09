package net.ink.api.member.component;

import net.ink.api.badge.dto.BadgeDto;
import net.ink.api.common.DtoCreator;
import net.ink.api.member.dto.MemberAchievementDto;
import net.ink.api.member.dto.MemberDto;
import net.ink.core.badge.entity.Badge;
import net.ink.core.common.EntityCreator;
import net.ink.core.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class MemberAchievementMapperTest {

    @Autowired
    private MemberAchievementMapper memberAchievementMapper;

    private static final List<Badge> badges = Arrays.asList(EntityCreator.createBadgeEntity(), EntityCreator.createBadgeEntity());
    private static final List<BadgeDto> badgeDtos = Arrays.asList(DtoCreator.createBadgeDto(), DtoCreator.createBadgeDto());

    private static final Member member = EntityCreator.createMemberEntity();
    private static final MemberDto memberDto = DtoCreator.createMemberDto();

    @Test
    public void 엔티티에서_DTO변환_테스트() {
        MemberAchievementDto mappedDto = memberAchievementMapper.toDto(member, badges, member);

        assertEquals(mappedDto.getMember().getEmail(), memberDto.getEmail());
        assertEquals(mappedDto.getBadges().size(), badgeDtos.size());
        assertEquals(mappedDto.getBadges().get(0).getName(), badgeDtos.get(0).getName());
        assertTrue(mappedDto.isRequesterProfile());
    }
}
