package net.ink.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordHintDto {
    private Long hintId;
    private String word;
    private String meaning;
}
