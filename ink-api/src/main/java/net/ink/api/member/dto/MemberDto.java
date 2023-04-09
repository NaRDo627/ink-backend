package net.ink.api.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.ink.api.core.util.NicknameNormalizer;
import net.ink.api.core.validation.annotation.KorEngOnly;
import net.ink.core.member.entity.MemberSetting;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="회원 모델", description="회원을 나타내는 모델")
public class MemberDto {
    @NotBlank
    @ApiModelProperty(value = "회원 식별값", required = true, position = PropertyDisplayOrder.IDENTIFIER)
    @JsonProperty(index = PropertyDisplayOrder.IDENTIFIER)
    private String identifier;

    @Email
    @ApiModelProperty(value = "이메일 주소", required = true, position = PropertyDisplayOrder.EMAIL)
    @JsonProperty(index = PropertyDisplayOrder.EMAIL)
    private String email;

    @KorEngOnly
    @NotBlank
    @ApiModelProperty(value = "닉네임", required = true, position = PropertyDisplayOrder.NICKNAME)
    @JsonProperty(index = PropertyDisplayOrder.NICKNAME)
    private String nickname;

    @ApiModelProperty(value = "프로필 이미지 경로", position = PropertyDisplayOrder.IMAGE)
    @JsonProperty(index = PropertyDisplayOrder.IMAGE)
    private String image;

    public void setNickname(String nickname) {
        this.nickname = NicknameNormalizer.normalize(nickname);
    }

    @SuperBuilder
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value="회원 읽기 모델", description="읽기 전용 회원 모델")
    public static class ReadOnly extends MemberDto {
        @ApiModelProperty(value = "회원 고유 아이디", accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                position = PropertyDisplayOrder.MEMBER_ID)
        @JsonProperty(index = PropertyDisplayOrder.MEMBER_ID)
        private Long memberId;

        @ApiModelProperty(value = "잉크 개수", accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                position = PropertyDisplayOrder.INK_COUNT)
        @JsonProperty(index = PropertyDisplayOrder.INK_COUNT)
        private int inkCount;

        @ApiModelProperty(value = "출석 일수", accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                position = PropertyDisplayOrder.ATTENDANCE_COUNT)
        @JsonProperty(index = PropertyDisplayOrder.ATTENDANCE_COUNT)
        private int attendanceCount;

        @ApiModelProperty(value = "마지막 출석 날짜", accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                position = PropertyDisplayOrder.LAST_ATTENDANCE_DATE)
        @JsonProperty(index = PropertyDisplayOrder.LAST_ATTENDANCE_DATE)
        private String lastAttendanceDate;

        @ApiModelProperty(value = "회원 설정 정보", accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                position = PropertyDisplayOrder.MEMBER_SETTING)
        @JsonProperty(index = PropertyDisplayOrder.MEMBER_SETTING)
        private MemberSetting memberSetting;
    }

    private static class PropertyDisplayOrder {
        private static final int MEMBER_ID              = 0;
        private static final int IDENTIFIER             = 1;
        private static final int EMAIL                  = 2;
        private static final int NICKNAME               = 3;
        private static final int IMAGE                  = 4;
        private static final int INK_COUNT           = 5;
        private static final int ATTENDANCE_COUNT       = 6;
        private static final int LAST_ATTENDANCE_DATE   = 7;
        private static final int MEMBER_SETTING         = 8;
    }
}
