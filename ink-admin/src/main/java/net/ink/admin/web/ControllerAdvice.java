package net.ink.admin.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String handleException(Model model) {
        model.addAttribute("content", "error/500");
        return "base";
    }
}
