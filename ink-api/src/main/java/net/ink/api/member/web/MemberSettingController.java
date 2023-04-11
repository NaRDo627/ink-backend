package net.ink.api.member.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.*;
import net.ink.api.core.annotation.CurrentUser;
import net.ink.api.core.dto.ApiResult;
import net.ink.api.member.component.MemberMapper;
import net.ink.api.member.dto.MemberDto;
import net.ink.core.member.entity.Member;
import net.ink.core.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "회원 설정 관련 엔드포인트", tags = "회원 설정 관련 엔드포인트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberSettingController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @ApiOperation(value = "좋아요 Push 알림 ON", notes = "좋아요 Push 알림을 켭니다.")
    @PutMapping("/me/setting/push/like")
    public ResponseEntity<ApiResult<MemberDto.ReadOnly>> likePushOn(@CurrentUser Member member,
                                                                    @ApiParam(value = "Push 설정 여부", required = true)
                                                                    @RequestBody PushActive pushActive) {
        member.getMemberSetting().setLikePushActive(pushActive.isPushActive());
        return ResponseEntity.ok(ApiResult.ok(memberMapper.toDto(
                memberService.updateMember(member)
        )));
    }

    @ApiOperation(value = "데일리 Push 알림 ON", notes = "데일리 Push 알림을 켭니다.")
    @PutMapping("/me/setting/push/daily")
    public ResponseEntity<ApiResult<MemberDto.ReadOnly>> dailyPushOn(@CurrentUser Member member,
                                                                     @ApiParam(value = "Push 설정 여부", required = true)
                                                                     @RequestBody PushActive pushActive) {
        member.getMemberSetting().setDailyPushActive(pushActive.isPushActive());
        return ResponseEntity.ok(ApiResult.ok(memberMapper.toDto(
                memberService.updateMember(member)
        )));
    }

    @NoArgsConstructor
    @Getter @Setter
    public static class PushActive {
        @ApiModelProperty(value = "푸시 알림 활성화 여부")
        private boolean pushActive;
    }
}
