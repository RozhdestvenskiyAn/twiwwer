package ru.rozhdestvenskiy.twiwwer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationReq {

    @NotBlank(message = "Nickname must not be empty")
    @Pattern(regexp = "^[A-Za-z\\d._-]{4,15}$", message = "Nickname is not valid")
    private String nickname;

    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "^[A-Za-z\\d.,:;_\\-?!+=/'\\\\\"*\\[\\]{}()]{8,100}$",
            message = "Password is not valid")
    private String password;
}
