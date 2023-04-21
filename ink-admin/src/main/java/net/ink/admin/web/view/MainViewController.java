package net.ink.admin.web.view;

import lombok.RequiredArgsConstructor;
import net.ink.admin.annotation.CurrentUser;
import net.ink.admin.entity.AdminMember;
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

    @GetMapping("/profile")
    public String getProfilePage(Model model, @CurrentUser AdminMember adminMember) {
        model.addAttribute("inner", "profile");
        model.addAttribute("adminMember", adminMember);
        return "base";
    }
}
