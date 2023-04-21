package net.ink.admin.web.view;

import lombok.RequiredArgsConstructor;
import net.ink.admin.annotation.AdminLogging;
import net.ink.admin.dto.mapper.MemberMapper;
import net.ink.core.member.service.MemberService;
import net.ink.core.todayexpression.repository.UsefulExpressionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class UsefulExprManagementController {
    private final UsefulExpressionRepository usefulExpressionRepository;

    @GetMapping("/useful-expression-management")
    public String getMemberManagement(Model model) {
        model.addAttribute("inner", "useful-expression-management");
        model.addAttribute("usefulExpressions", usefulExpressionRepository.findAll());
        return "base";
    }
}
