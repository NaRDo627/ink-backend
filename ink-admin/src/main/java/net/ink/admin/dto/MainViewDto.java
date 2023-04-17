package net.ink.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MainViewDto {
    private int questionCount;
    private int registeredReplyCount;
    private int totalMemberCount;
}
