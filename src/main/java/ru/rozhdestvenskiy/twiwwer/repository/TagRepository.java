package ru.rozhdestvenskiy.twiwwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.twiwwer.model.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsTagByTag(String tag);

    Optional<Tag> findByTag(String tag);

}
