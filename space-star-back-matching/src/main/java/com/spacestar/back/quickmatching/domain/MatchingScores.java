package com.spacestar.back.quickmatching.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MatchingScores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(length = 10)
    private String myId;
    @NotNull
    @Column(length = 10)
    private String yourId;
    @NotNull
    @Column(length = 10)
    private int score;
    @Builder
    public MatchingScores(long id, String myId, String yourId, int score) {
        this.id = id;
        this.myId = myId;
        this.yourId = yourId;
        this.score = score;
    }
}