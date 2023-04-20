package net.ink.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordHintDto {
    private Long hintId;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9\\s\\\\!@#$%^&*(),.?\":{}|<>]+$", message = "Word must only contain English letters, numbers, and special characters.")
    private String word;

    @NotEmpty
    private String meaning;
}
