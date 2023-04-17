package net.ink.admin.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.ink.admin.entity.AdminMember;
import net.ink.admin.service.AdminMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final AdminMemberService adminMemberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Validate the registration request (e.g., check if passwords match, etc.)
        // If the validation fails, return an error message or redirect to an error page.

        // Create a new AdminMember entity using the registerRequest
        AdminMember newAdmin = AdminMember.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .nickname(registerRequest.getNickname())
                .build();

        // Save the new AdminMember using the AdminUserDetailsService or a custom service
        adminMemberService.saveAdminMember(newAdmin);

        // Redirect the user to the login page or another page
        return ResponseEntity.ok(new RegisterResponse("ok"));
    }

    @Data
    public static class RegisterRequest {
        private String username;
        private String nickname;
        private String password;
    }

    @AllArgsConstructor @Getter
    public static class RegisterResponse {
        private String result;
    }
}
