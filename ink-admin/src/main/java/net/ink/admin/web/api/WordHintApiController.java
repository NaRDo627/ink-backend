package net.ink.admin.web.api;

import lombok.RequiredArgsConstructor;
import net.ink.admin.dto.QuestionDto;
import net.ink.admin.dto.WordHintDto;
import net.ink.admin.dto.mapper.QuestionMapper;
import net.ink.admin.dto.mapper.WordHintMapper;
import net.ink.core.core.exception.EntityNotFoundException;
import net.ink.core.question.entity.Question;
import net.ink.core.question.entity.WordHint;
import net.ink.core.question.repository.QuestionRepository;
import net.ink.core.question.repository.WordHintRepository;
import net.ink.core.question.service.QuestionService;
import net.ink.core.question.service.WordHintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WordHintApiController {
    private final QuestionService questionService;
    private final WordHintService wordHintService;
    private final WordHintMapper wordHintMapper;

    // Add word hint
    @PostMapping("/question/{questionId}/word-hint")
    public ResponseEntity<?> addWordHint(@PathVariable Long questionId, @Valid @RequestBody WordHintDto wordHintDto) {
        Question question = questionService.getQuestionById(questionId);
        WordHint wordHint = wordHintMapper.toEntity(wordHintDto);
        question.getWordHints().add(wordHint);
        wordHint.setQuestion(question);
        questionService.update(question);
        return ResponseEntity.ok().build();
    }

    // Update word hint
    @PutMapping("/word-hint/{hintId}")
    public ResponseEntity<?> updateWordHint(@PathVariable Long hintId, @Valid @RequestBody WordHintDto wordHintDto) {
        wordHintDto.setHintId(hintId);
        wordHintService.update(wordHintMapper.toEntity(wordHintDto));
        return ResponseEntity.ok().build();
    }

    // Delete word hint
    @DeleteMapping("/word-hint/{hintId}")
    public ResponseEntity<?> deleteWordHint(@PathVariable Long hintId) {
        wordHintService.deleteById(hintId);
        return ResponseEntity.ok().build();
    }
}