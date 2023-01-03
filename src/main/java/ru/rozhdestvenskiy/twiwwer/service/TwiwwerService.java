package ru.rozhdestvenskiy.twiwwer.service;

import org.springframework.http.ResponseEntity;
import ru.rozhdestvenskiy.twiwwer.domain.api.request.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;
import ru.rozhdestvenskiy.twiwwer.dto.PhraseDto;
import ru.rozhdestvenskiy.twiwwer.dto.UserDto;

public interface TwiwwerService {

    void createPhrase(PhraseDto phraseDto);
}
