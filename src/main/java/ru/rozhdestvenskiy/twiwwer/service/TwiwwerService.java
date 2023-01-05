package ru.rozhdestvenskiy.twiwwer.service;

import ru.rozhdestvenskiy.twiwwer.dto.PhraseDto;

import java.util.List;

public interface TwiwwerService {

    void createPhrase(PhraseDto phraseDto);

    List<PhraseDto> getAllMyPhrases();
}
