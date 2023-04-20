package net.ink.admin.web.view;

import lombok.RequiredArgsConstructor;
import net.ink.admin.dto.mapper.MemberMapper;
import net.ink.admin.repository.AdminMemberRepository;
import net.ink.core.member.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class AdminManagementController {
    private final AdminMemberRepository adminMemberRepository;

    @PreAuthorize("hasAuthority('ROLE_SUPERVISOR')")
    @GetMapping("/admin-management")
    public String getMemberManagement(Model model) {
        model.addAttribute("inner", "admin-management");
        model.addAttribute("adminMembers", adminMemberRepository.findAll());
        return "base";
    }
}
