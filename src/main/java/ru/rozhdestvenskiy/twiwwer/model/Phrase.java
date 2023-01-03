package ru.rozhdestvenskiy.twiwwer.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "tags"})
@ToString(exclude = {"user", "tags"})
@Builder
@Entity
@Table(name = "phrase")
public class Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;
    private LocalDateTime timeInsert;

    @Builder.Default
    @ManyToMany(cascade = {

            CascadeType.MERGE
    })
    @JoinTable(name = "phrase_tag",
            joinColumns = @JoinColumn(name = "phrase_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

}
