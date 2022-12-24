package ru.rozhdestvenskiy.twiwwer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.rozhdestvenskiy.twiwwer.domain.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;
import ru.rozhdestvenskiy.twiwwer.util.ValidationUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirstServiceImpl implements FirstService{

    private final ValidationUtils validationUtils;
    public ResponseEntity<Response> registration(RegistrationReq registrationReq) {

        validationUtils.validationRequest(registrationReq);

        return null;
    }
}
