package ru.rozhdestvenskiy.twiwwer.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    @ManyToMany(mappedBy = "tags", cascade = {
            CascadeType.MERGE
    })
    private Set<Phrase> phrases = new HashSet<>();
}
