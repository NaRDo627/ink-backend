package net.ink.core.badge.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.badge.entity.Badge;
import net.ink.core.badge.repository.BadgeRepository;
import net.ink.core.core.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.ink.core.core.message.ErrorMessage.NOT_EXIST_BADGE;

@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Transactional(readOnly = true)
    public Badge findById(Long badgeId){
        return badgeRepository.findById(badgeId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST_BADGE));
    }
}
