package net.ink.core.question.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.core.exception.EntityNotFoundException;
import net.ink.core.question.entity.WordHint;
import net.ink.core.question.repository.WordHintRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WordHintService {
    private final WordHintRepository wordHintRepository;

    @Transactional
    public void update(WordHint wordHint) {
        WordHint oldWordHint = wordHintRepository.findById(wordHint.getHintId()).orElseThrow(EntityNotFoundException::new);
        oldWordHint.setWord(wordHint.getWord());
        oldWordHint.setMeaning(wordHint.getMeaning());
        wordHintRepository.saveAndFlush(oldWordHint);
    }

    @Transactional
    public void deleteById(Long hintId) {
        wordHintRepository.deleteById(hintId);
    }
}
