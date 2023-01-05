package ru.rozhdestvenskiy.twiwwer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.twiwwer.dto.PhraseDto;
import ru.rozhdestvenskiy.twiwwer.mapper.PhraseMapper;
import ru.rozhdestvenskiy.twiwwer.mapper.UserMapper;
import ru.rozhdestvenskiy.twiwwer.model.Phrase;
import ru.rozhdestvenskiy.twiwwer.model.Tag;
import ru.rozhdestvenskiy.twiwwer.model.User;
import ru.rozhdestvenskiy.twiwwer.repository.PhraseRepository;
import ru.rozhdestvenskiy.twiwwer.repository.TagRepository;
import ru.rozhdestvenskiy.twiwwer.security.service.UserDetailsImpl;
import ru.rozhdestvenskiy.twiwwer.service.TwiwwerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwiwwerServiceImpl implements TwiwwerService {
    private final PhraseMapper phraseMapper;
    private final PhraseRepository phraseRepository;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public void createPhrase(PhraseDto phraseDto) {

        log.info("Mapping phraseDto: {}", phraseDto);
        Phrase phrase = phraseMapper.phraseDtoMapToPhrase(phraseDto);

        log.info("Setting time insert and owner of the phrase");
        phrase.setTimeInsert(LocalDateTime.now());
        phrase.setUser(getCurrentUser());

        phrase.getTags().stream()
                .peek(tag -> tagRepository.findByTag(tag.getTag()).ifPresent(val -> tag.setId(val.getId())))
                .filter(tag -> tag.getId()==null)
                .peek(tag -> log.info("Saving tag: {}", tag))
                .forEach(tagRepository::save);

        log.info("Saving phrase: {}", phrase);
        phraseRepository.save(phrase);
    }

    @Override
    public List<PhraseDto> getAllMyPhrases() {

        User currentUser = getCurrentUser();

        log.info("Getting phrases by current user: {}", currentUser);
        List<Phrase> phrases = phraseRepository.findAllByUserOrderByTimeInsertDesc(currentUser);

        log.info("Mapping phrase list to dto: {}", phrases);
        return phrases.stream().map(phraseMapper::phraseMapToPhraseDto).collect(Collectors.toList());
    }

    private User getCurrentUser() {

        log.info("Getting owner of phrase");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        log.info("Got owner of phrase: {}", user);
        return user;
    }
}
