package ru.rozhdestvenskiy.twiwwer.contrroler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rozhdestvenskiy.twiwwer.domain.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;
import ru.rozhdestvenskiy.twiwwer.service.TwiwwerService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TwiwwerController {

    private final TwiwwerService twiwwerService;

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationReq registrationReq) {

        log.info("START endpoint registration, request: {}", registrationReq);
        ResponseEntity<Response> registrationRes = twiwwerService.registration(registrationReq);
        log.info("END endpoint registration, response: {}", registrationRes);

        // TODO: 25.12.2022  return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
