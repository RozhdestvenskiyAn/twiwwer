package ru.rozhdestvenskiy.twiwwer.domain.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicPhraseReq {

    @NotBlank(message = "Text must not be empty")
    @Size(max = 150, message = "Text must be between 1 and 150 characters")
    private String text;

    @Size(max = 5, message = "the number of tags should not exceed 5")
    private Set<
            @NotBlank(message = "Tag must not be empty")
    @Pattern(regexp = "^[A-Za-zА-Яа-я\\d]+$", message = "Tag is not valid")
    @Size(min = 3, max = 25, message = "Tag must be between 3 and 25 characters")
            String> tags = new HashSet<>();
}
