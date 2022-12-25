package ru.rozhdestvenskiy.twiwwer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email is not valid")
    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    private String email;
}
