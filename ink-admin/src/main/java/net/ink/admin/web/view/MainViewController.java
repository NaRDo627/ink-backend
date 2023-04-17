package net.ink.admin.web.view;

import lombok.RequiredArgsConstructor;
import net.ink.admin.service.view.MainViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainViewController {
    private final MainViewService mainViewService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping(value = {"/", "/main"})
    public String getMainPage(Model model) {
        model.addAttribute("inner", "main");
        model.addAttribute("mainView", mainViewService.getDashboardItem());
        return "base";
    }

    @GetMapping("/question-management")
    public String getQuestionManagement(Model model) {
        model.addAttribute("inner", "question-management");
        return "base";
    }

    @GetMapping("/reply-management")
    public String getReplyManagement(Model model) {
        model.addAttribute("inner", "reply-management");
        return "base";
    }


}
