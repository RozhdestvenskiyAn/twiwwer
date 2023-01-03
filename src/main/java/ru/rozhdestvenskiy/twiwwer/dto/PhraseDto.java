package ru.rozhdestvenskiy.twiwwer.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class PhraseDto {
    private Long id;
    private String text;
    private UserDto userDto;
    private LocalDateTime timeInsert;
    private Set<TagDto> tags;
}
