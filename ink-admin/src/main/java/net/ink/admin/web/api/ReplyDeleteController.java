package net.ink.admin.web.api;

import lombok.RequiredArgsConstructor;
import net.ink.admin.annotation.AdminLogging;
import net.ink.core.member.service.MemberService;
import net.ink.core.reply.repository.ReplyRepository;
import net.ink.core.reply.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyDeleteController {
    private final ReplyRepository replyRepository;

    @AdminLogging
    @DeleteMapping("/api/reply/{replyId}")
    public ResponseEntity<?> deleteReply(@PathVariable Long replyId) {
        replyRepository.deleteById(replyId);
        return ResponseEntity.ok().build();
    }
}
