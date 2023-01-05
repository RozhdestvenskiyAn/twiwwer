package ru.rozhdestvenskiy.twiwwer.domain.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {

    @NotBlank(message = "Nickname must not be empty")
    private String nickname;

    @NotBlank(message = "Password must not be empty")
    private String password;
}
