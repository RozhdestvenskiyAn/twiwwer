package ru.rozhdestvenskiy.twiwwer.domain.api.phrase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhraseRes {
    long id;
    String text;
    LocalDateTime timeInsert;
    Set<String> tags;
}
