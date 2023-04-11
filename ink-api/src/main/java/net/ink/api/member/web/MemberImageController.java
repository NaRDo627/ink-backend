package net.ink.api.member.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.ink.api.core.dto.ApiResult;
import net.ink.api.member.service.MemberImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "회원 관련 엔드포인트", tags = "회원 관련 엔드포인트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberImageController {
    private final MemberImageService memberImageService;

    @ApiOperation(value = "사용자 이미지 업로드", notes = "사용자 프로필 이미지를 업로드합니다.")
    @PostMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResult<ImagePathDto>> postImage(
            @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(ApiResult.ok(new ImagePathDto(memberImageService.uploadMemberImageFile(image))));
    }

    @Getter
    @AllArgsConstructor
    public static class ImagePathDto {
        private final String imagePath;
    }
}
