package com.spacestar.back.profile.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LikedGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gameId;

    @NotNull
    @Column(length = 100)
    private String uuid;

    @Builder

    public LikedGame(Long id, Long gameId, String uuid) {
        this.id = id;
        this.gameId = gameId;
        this.uuid = uuid;
    }
}
