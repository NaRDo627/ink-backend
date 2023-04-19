package net.ink.admin.web.view.advice;

import java.security.Principal;

import net.ink.admin.dto.AdminUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserInfo(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("nickname", username);
        }
    }
}
