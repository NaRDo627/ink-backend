package net.ink.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsefulExpressionDto {
    private Long expId;

    @NotEmpty
    private String expression;

    @NotEmpty
    private String meaning;

    @NotEmpty
    private String expressionExample;

    @NotEmpty
    private String expressionExampleMeaning;
}
