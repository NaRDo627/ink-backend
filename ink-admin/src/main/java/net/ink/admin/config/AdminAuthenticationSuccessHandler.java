package net.ink.admin.config;

import net.ink.admin.dto.AdminUser;
import net.ink.admin.entity.AdminMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AdminAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Get the authenticated user
        AdminUser user = (AdminUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the user's rank is PENDING
        if (user.getRank() == AdminMember.RANK.PENDING) {
            // Redirect to a specific page for PENDING users
            getRedirectStrategy().sendRedirect(request, response, "/login?pending");
            return;
        }

        // Redirect to the default success URL ("/main")
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
