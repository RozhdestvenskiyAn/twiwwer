package ru.rozhdestvenskiy.twiwwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.twiwwer.model.Phrase;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Long> {

}
