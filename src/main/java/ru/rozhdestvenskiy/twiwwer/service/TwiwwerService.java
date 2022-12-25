package ru.rozhdestvenskiy.twiwwer.service;

import org.springframework.http.ResponseEntity;
import ru.rozhdestvenskiy.twiwwer.domain.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;

public interface TwiwwerService {
    ResponseEntity<Response> registration (RegistrationReq registrationReq);
}
