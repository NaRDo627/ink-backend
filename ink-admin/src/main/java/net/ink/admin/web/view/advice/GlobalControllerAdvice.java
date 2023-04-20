package net.ink.admin.web.view.advice;

import java.security.Principal;

import net.ink.admin.annotation.CurrentUser;
import net.ink.admin.dto.AdminUser;
import net.ink.admin.entity.AdminMember;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserInfo(Model model, @CurrentUser AdminMember adminMember) {
        if (adminMember != null) {
            model.addAttribute("adminMember", adminMember);
        }
    }
}
