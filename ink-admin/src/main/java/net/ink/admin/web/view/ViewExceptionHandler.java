package net.ink.admin.web.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Priority;

@Priority(20)
@org.springframework.web.bind.annotation.ControllerAdvice
public class ViewExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Model model) {
        model.addAttribute("inner", "error/500");
        return "base";
    }
}
