package ru.rozhdestvenskiy.twiwwer.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.twiwwer.model.Phrase;
import ru.rozhdestvenskiy.twiwwer.model.User;

import java.util.List;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Long> {
    @EntityGraph(
            attributePaths = {
                    "user",
                    "tags"
            }
    )
    List<Phrase> findAllByUserOrderByTimeInsertDesc(User user);
}
