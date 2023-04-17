package net.ink.admin.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.ink.admin.entity.AdminMember;
import net.ink.admin.service.AdminMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.*;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final AdminMemberService adminMemberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
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
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new RegisterResponse("ok"));
    }

    @Data
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 6, max = 14)
        @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]+$", message = "영문자 또는 영문자와 숫자 조합으로 입력해주세요. (숫자만 사용할 수 없습니다)")
        private String username;

        @NotBlank
        @Size(min = 2, max = 8)
        private String nickname;

        @NotBlank
        @Size(min = 8)
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\\W_]).{8,}$", message = "비밀번호는 영문자, 숫자, 특수문자의 조합으로 입력해주세요.")
        private String password;
    }

    @AllArgsConstructor @Getter
    public static class RegisterResponse {
        private String result;
    }
}
