package net.ink.admin.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminMemberDto {
    private Long adminId;
    private String username;
    private String password;
    private String nickname;
    private Boolean isActive;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}