package net.ink.admin.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppViewController {

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
        return "base";
    }

    @GetMapping("/buttons")
    public String getButtonsPage(Model model) {
        model.addAttribute("inner", "buttons");
        return "base";
    }

    @GetMapping("/cards")
    public String getCardsPage(Model model) {
        model.addAttribute("inner", "cards");
        return "base";
    }

    @GetMapping("/tables")
    public String getTablesPage(Model model) {
        model.addAttribute("inner", "tables");
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

    @GetMapping("/exception")
    public String exception(Model model) {
        throw new RuntimeException("Exception test");
    }
}
