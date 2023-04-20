package net.ink.admin.web.api;

import lombok.RequiredArgsConstructor;
import net.ink.admin.annotation.AdminLogging;
import net.ink.admin.dto.QuestionDto;
import net.ink.admin.dto.mapper.QuestionMapper;
import net.ink.core.question.entity.Question;
import net.ink.core.question.repository.QuestionRepository;
import net.ink.core.question.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionApiController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @AdminLogging
    @PostMapping("/question")
    public ResponseEntity<?> addQuestion(@Valid @RequestBody QuestionDto questionDto) {
        questionService.create(questionMapper.toEntity(questionDto, null));
        return ResponseEntity.ok().build();
    }

    @AdminLogging
    @PutMapping("/question/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @Valid @RequestBody QuestionDto questionDto) {
        Question question = questionService.getQuestionById(questionId);
        question.setContent(questionDto.getContent());
        question.setKoContent(questionDto.getKoContent());
        questionService.update(question);
        return ResponseEntity.ok().build();
    }

    @AdminLogging
    @DeleteMapping("/question/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionService.deleteById(questionId);
        return ResponseEntity.ok().build();
    }
}