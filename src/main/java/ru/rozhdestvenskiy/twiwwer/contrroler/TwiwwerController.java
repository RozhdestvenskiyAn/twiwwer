package ru.rozhdestvenskiy.twiwwer.contrroler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rozhdestvenskiy.twiwwer.domain.api.phrase.PhraseRes;
import ru.rozhdestvenskiy.twiwwer.domain.api.phrase.PublicPhraseReq;
import ru.rozhdestvenskiy.twiwwer.domain.api.phrase.MyPhrasesRes;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;
import ru.rozhdestvenskiy.twiwwer.domain.responce.SuccessResponse;
import ru.rozhdestvenskiy.twiwwer.dto.PhraseDto;
import ru.rozhdestvenskiy.twiwwer.mapper.PhraseMapper;
import ru.rozhdestvenskiy.twiwwer.service.TwiwwerService;
import ru.rozhdestvenskiy.twiwwer.util.ValidationUtils;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TwiwwerController {

    private final ValidationUtils validationUtils;

    private final TwiwwerService twiwwerService;
    private final PhraseMapper phraseMapper;

    @PostMapping("/phrase")
    public ResponseEntity<Response> publicPhrase(@RequestBody PublicPhraseReq publicPhraseReq) {
        log.info("START endpoint public phrase, request: {}", publicPhraseReq);

        log.info("Validation public phrase, request: {}", publicPhraseReq);
        validationUtils.validationRequest(publicPhraseReq);

        log.info("Mapping public phrase request: {}", publicPhraseReq);
        PhraseDto phraseDto = phraseMapper.publicPhraseReqMapToPhraseDto(publicPhraseReq);

        twiwwerService.createPhrase(phraseDto);

        ResponseEntity<Response> response = new ResponseEntity<>(HttpStatus.OK);
        log.info("END endpoint public phrase, response: {}", response);
        return response;
    }

    @GetMapping("/myPhrases")
    public ResponseEntity<Response> getMyPhrases() {
        log.info("START endpoint get my phrase");

        List<PhraseDto> phraseDtoList = twiwwerService.getAllMyPhrases();
        log.info("Got list of phrases size:{}", phraseDtoList.size());

        log.info("Mapping phrase response");
        List<PhraseRes> phraseResList = phraseMapper.phraseDtoListMapToPhraseRespList(phraseDtoList);

        ResponseEntity<Response> response = ResponseEntity.ok()
                .body(SuccessResponse.builder()
                        .data(MyPhrasesRes.builder()
                                .phrases(phraseResList)
                                .build())
                        .build());
        log.info("END endpoint get my phrase");
        return response;
    }

}
