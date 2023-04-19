package net.ink.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long questionId;
    private String category;

    @NotEmpty @Size(max = 73)
    private String content;

    @Size(max = 45)
    private String koContent;
    private String authorName;
    private int repliesCount;
    private Set<WordHintDto> wordHints;
}
