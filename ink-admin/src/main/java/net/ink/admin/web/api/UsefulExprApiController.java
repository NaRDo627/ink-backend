package net.ink.admin.web.api;

import lombok.RequiredArgsConstructor;
import net.ink.admin.annotation.AdminLogging;
import net.ink.admin.dto.UsefulExpressionDto;
import net.ink.admin.dto.mapper.UsefulExpressionMapper;
import net.ink.core.todayexpression.repository.UsefulExpressionRepository;
import net.ink.core.todayexpression.service.UsefulExpressionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UsefulExprApiController {
    private final UsefulExpressionService usefulExpressionService;
    private final UsefulExpressionMapper usefulExpressionMapper;

    @AdminLogging
    @DeleteMapping("/api/useful-expression/{usefulExprId}")
    public ResponseEntity<?> deleteUsefulExpression(@PathVariable Long usefulExprId) {
        usefulExpressionService.deleteById(usefulExprId);
        return ResponseEntity.ok().build();
    }

    @AdminLogging
    @PostMapping("/api/useful-expression")
    public ResponseEntity<?> addUsefulExpression(@RequestBody UsefulExpressionDto usefulExpressionDto) {
        usefulExpressionService.addUsefulExpression(usefulExpressionMapper.toEntity(usefulExpressionDto));
        return ResponseEntity.ok().build();
    }
}
