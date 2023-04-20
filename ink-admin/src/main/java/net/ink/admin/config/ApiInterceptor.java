package net.ink.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        log.info("Request URL: {} | Start Time: {}", request.getRequestURL(), startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) {
        long endTime = System.currentTimeMillis();
        long startTime = (Long) request.getAttribute("startTime");
        log.info("Request URL: {} | End Time: {} | Time Taken: {} ms", request.getRequestURL(), endTime, (endTime - startTime));
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
    }
}
