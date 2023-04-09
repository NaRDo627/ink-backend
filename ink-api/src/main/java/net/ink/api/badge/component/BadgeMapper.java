package net.ink.api.badge.component;

import net.ink.api.badge.dto.BadgeDto;
import net.ink.core.badge.entity.Badge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BadgeMapper {
    BadgeDto toDto(Badge badge);
}
