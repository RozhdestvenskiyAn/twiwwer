package ru.rozhdestvenskiy.twiwwer.domain.api.phrase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPhrasesRes {
    List<PhraseRes>phrases;
}
