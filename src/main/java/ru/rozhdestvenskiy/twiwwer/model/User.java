package ru.rozhdestvenskiy.twiwwer.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "phrases")
@ToString(exclude = "phrases")
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nickname;
    private String password;
    private String email;
    private LocalDateTime timeInsert;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Phrase> phrases = new ArrayList<>();
}
