package net.ink.admin.web.view;

import lombok.RequiredArgsConstructor;
import net.ink.admin.dto.mapper.MemberMapper;
import net.ink.admin.dto.mapper.QuestionMapper;
import net.ink.core.member.service.MemberService;
import net.ink.core.question.repository.QuestionRepository;
import net.ink.core.question.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class QuestionManagementController {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @GetMapping("/question-management")
    public String getQuestionManagement(Model model) {
        model.addAttribute("inner", "question-management");
        model.addAttribute("questions", questionRepository.findAll().stream()
                .map(questionMapper::toDto).collect(Collectors.toList()));
        return "base";
    }
}
