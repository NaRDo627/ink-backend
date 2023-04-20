package net.ink.admin.web.api;

import lombok.RequiredArgsConstructor;
import net.ink.admin.annotation.AdminLogging;
import net.ink.admin.service.AdminMemberService;
import net.ink.core.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminApiController {
    private final AdminMemberService adminMemberService;

    @AdminLogging
    @PreAuthorize("hasAuthority('ROLE_SUPERVISOR')")
    @DeleteMapping("/api/admin-member/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
        adminMemberService.deleteAdminMemberById(memberId);
        return ResponseEntity.ok().build();
    }

    @AdminLogging
    @PreAuthorize("hasAuthority('ROLE_SUPERVISOR')")
    @PostMapping("/api/admin-member/{memberId}/promote")
    public ResponseEntity<?> promoteMember(@PathVariable Long memberId) {
        adminMemberService.promoteAdminMemberById(memberId);
        return ResponseEntity.ok().build();
    }
}
