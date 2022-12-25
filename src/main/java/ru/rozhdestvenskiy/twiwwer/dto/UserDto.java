package ru.rozhdestvenskiy.twiwwer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String nickname;
    private String password;
    private String email;
    private LocalDateTime timeInsert;
}
