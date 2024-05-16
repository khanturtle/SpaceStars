package com.spacestar.back.game.domain;

import com.spacestar.back.gamegenre.domain.GameGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private GameGenre gameGenre;
    @NotNull
    private String name;
    @NotNull
    private String name_kor;
    @NotNull
    private String image;
    @NotNull
    private boolean is_tier;
    @NotNull
    private boolean is_position;
    @NotNull
    private boolean is_class;
    @NotNull
    private boolean is_server;
}
