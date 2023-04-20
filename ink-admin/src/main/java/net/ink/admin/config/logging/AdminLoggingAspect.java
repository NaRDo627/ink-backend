package net.ink.admin.config.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.admin.dto.AdminUser;
import net.ink.admin.entity.AdminMember;
import net.ink.admin.repository.AdminLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminLoggingAspect {
    private final ApplicationEventPublisher eventPublisher;
    private final HttpServletRequest request;

    @Pointcut("@annotation(net.ink.admin.annotation.AdminLogging)")
    public void adminLoggingPointcut() {
    }

    @Around("adminLoggingPointcut()")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return result;
        }

        AdminUser user = (AdminUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminMember adminMember = user.getAdminMember();
        String methodName = joinPoint.getSignature().getName();
        String requestBody = (String) request.getAttribute("requestBody");

        log.info("Admin User Email: {},Method Name: {}, Request Body: {}", adminMember.getEmail(), methodName, requestBody);
        eventPublisher.publishEvent(new AdminLoggingEvent(adminMember, methodName, requestBody));

        return result;
    }

    private String readRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

}
