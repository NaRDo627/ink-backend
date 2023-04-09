package net.ink.api.todayexpression.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.ink.api.core.annotation.CurrentUser;
import net.ink.api.core.dto.ApiResult;
import net.ink.api.todayexpression.component.TodayExpressionMapper;
import net.ink.api.todayexpression.dto.UsefulExpressionDto;
import net.ink.core.member.entity.Member;
import net.ink.core.todayexpression.service.UsefulExpressionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "오늘의 표현 엔드포인트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/today-expression")
public class TodayExpressionController {
    private final UsefulExpressionService usefulExpressionService;
    private final TodayExpressionMapper todayExpressionMapper;

    @ApiOperation(value = "오늘의 표현 가져오기", notes = "오늘의 표현을 가져옵니다.")
    @GetMapping
    public ResponseEntity<ApiResult<List<UsefulExpressionDto>>> getTodayExpressions(@CurrentUser Member member){
        return ResponseEntity.ok(ApiResult.ok(
                usefulExpressionService.getTodayExpressions().stream()
                .map(x -> todayExpressionMapper.toDto(x, member))
                .collect(Collectors.toList())
        ));
    }
}
