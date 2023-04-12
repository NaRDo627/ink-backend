package net.ink.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/", "/main"})
    public String getMainPage(Model model) {
        model.addAttribute("content", "main");
        return "base";
    }

    @GetMapping("/buttons")
    public String getButtonsPage(Model model) {
        model.addAttribute("content", "buttons");
        return "base";
    }

    @GetMapping("/cards")
    public String getCardsPage(Model model) {
        model.addAttribute("content", "cards");
        return "base";
    }

    @GetMapping("/exception")
    public String exception(Model model) {
        throw new RuntimeException("Exception test");
    }
}
