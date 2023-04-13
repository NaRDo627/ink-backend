package net.ink.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.ink.core.member.entity.MemberSetting;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;
    private int inkCount;
    private int attendanceCount;
    private String lastAttendanceDate;
    private String identifier;
    private String email;
    private String nickname;
    private String image;
    private Boolean isActive;
}
