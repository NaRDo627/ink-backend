package net.ink.admin.web.api;

import lombok.RequiredArgsConstructor;
import net.ink.admin.annotation.AdminLogging;
import net.ink.admin.service.AdminMemberService;
import net.ink.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberDeleteController {
    private final MemberService memberService;

    @AdminLogging
    @DeleteMapping("/api/member/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
        memberService.dropOutMember(memberService.findById(memberId)); // TODO : 하드 삭제로 변경
        return ResponseEntity.ok().build();
    }
}
