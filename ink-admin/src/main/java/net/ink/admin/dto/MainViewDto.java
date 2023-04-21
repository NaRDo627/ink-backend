package net.ink.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ink.core.todayexpression.entity.TodayUsefulExpression;

import java.util.List;

@Data
@Builder
public class MainViewDto {
    private int questionCount;
    private int registeredReplyCount;
    private int totalMemberCount;
    private List<UsefulExpressionDto> todayUsefulExpressions;
}
